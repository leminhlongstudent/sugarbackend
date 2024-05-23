package site.sugarnest.backend.service.product;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import site.sugarnest.backend.dto.dto.ProductDto;
import site.sugarnest.backend.entities.ImageProductEntity;

import site.sugarnest.backend.entities.*;
import site.sugarnest.backend.exception.ResourceNotFoundException;
import site.sugarnest.backend.mapper.IProductMapper;
import site.sugarnest.backend.reponsitoties.IProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductService implements IProductService {
    private IProductRepository iProductRepository;
    private IProductMapper iProductMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        // Ánh xạ ProductDto sang Product
        ProductEntity productEntity = iProductMapper.mapToProduct(productDto);

        // Khởi tạo ProductPrice và gán Product
        ProductPriceEntity productPrice = new ProductPriceEntity();
        System.out.println(productDto);
        productPrice.setProductEntity(productEntity);
        productPrice.setListPrice(productEntity.getProductPriceEntity().getListPrice());
        productPrice.setDiscount(productEntity.getProductPriceEntity().getDiscount());

        // Tạo danh sách ImageProduct từ ProductDto
        List<ImageProductEntity> imageProductEntities = productDto.getImageProducts().stream()
                .map(imageDto -> {
                    ImageProductEntity imageProductEntity = iProductMapper.mapToImageProduct(imageDto);
                    imageProductEntity.setProductEntity(productEntity);
                    return imageProductEntity;
                }).collect(Collectors.toList());

        //Tạo danh sách SizeColorProduct từ ProductDto
        List<SizeColorProductEntity> sizeColorProducts = productDto.getSizeColorProductsEntity().stream()
                .map(sizeColorProductDto -> {
                    SizeColorProductEntity sizeColorProductEntity = iProductMapper.mapToSizeColorProduct(sizeColorProductDto);
                    sizeColorProductEntity.setProductEntity(productEntity);
                    return sizeColorProductEntity;
                }).collect(Collectors.toList());
        // Gán các giá trị
        productEntity.setProductPriceEntity(productPrice);
        productEntity.setImageProducts(imageProductEntities);
        productEntity.setSizeColorProductsEntity(sizeColorProducts);

        // Lưu Product vào cơ sở dữ liệu
        ProductEntity productSave = iProductRepository.save(productEntity);

        // Trả về ProductDto tương ứng với Product đã lưu
        return iProductMapper.mapToProductDto(productSave);
    }

    @Override
    public Page<ProductDto> getAllProduct(Pageable pageable) {
        Page<ProductEntity> products = iProductRepository.findAll(pageable);
        List<ProductDto> productDtos = products.stream().map(product -> iProductMapper.mapToProductDto(product)).collect(Collectors.toList());

        return new PageImpl<>(productDtos, pageable, products.getTotalElements());
    }

    @Override
    public List<ProductDto> findProductByCategoryId(Long categoryId, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<ProductEntity> products = iProductRepository.findProductByCategoryId(categoryId, pageable);
        return products.stream().map(product -> iProductMapper.mapToProductDto(product)).collect(Collectors.toList());
    }
    @Override
    public ProductDto getProductById(Long productId) {
        ProductEntity product = iProductRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product is not exist with given id: " + productId));
        return iProductMapper.mapToProductDto(product);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto updateProductDto) {
        // Kiểm tra xem sản phẩm có tồn tại trong cơ sở dữ liệu không
        ProductEntity existingProduct = iProductRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product is not exist with given id: " + productId));

        // Ánh xạ thông tin từ updateProductDto sang existingProduct
        existingProduct.setNameProduct(updateProductDto.getNameProduct());
        existingProduct.setDescription(updateProductDto.getDescription());
        existingProduct.setSupplierEntity(updateProductDto.getSupplierEntity());
        existingProduct.setProducerEntity(updateProductDto.getProducerEntity());
        existingProduct.setCategoryEntity(updateProductDto.getCategoryEntity());
        existingProduct.setSubCategoryEntity(updateProductDto.getSubCategoryEntity());
        existingProduct.setIsActive(updateProductDto.getIsActive());
        existingProduct.setIsDelete(updateProductDto.getIsDelete());
        existingProduct.setStatus(updateProductDto.getStatus());

        // Cập nhật ProductPrice
        ProductPriceEntity productPrice = existingProduct.getProductPriceEntity();
        productPrice.setListPrice(updateProductDto.getProductPriceEntity().getListPrice());
        productPrice.setDiscount(updateProductDto.getProductPriceEntity().getDiscount());

        // Cập nhật danh sách ImageProduct
        List<ImageProductEntity> imageProductEntities = updateProductDto.getImageProducts().stream()
                .map(imageDto -> {
                    ImageProductEntity imageProductEntity = iProductMapper.mapToImageProduct(imageDto);
                    imageProductEntity.setProductEntity(existingProduct);
                    return imageProductEntity;
                }).collect(Collectors.toList());
        existingProduct.setImageProducts(imageProductEntities);

        // Cập nhật danh sách SizeColorProduct
        List<SizeColorProductEntity> sizeColorProductEntities = updateProductDto.getSizeColorProductsEntity().stream()
                .map(sizeColorProductDto -> {
                    SizeColorProductEntity sizeColorProductEntity = iProductMapper.mapToSizeColorProduct(sizeColorProductDto);
                    sizeColorProductEntity.setProductEntity(existingProduct);
                    return sizeColorProductEntity;
                }).collect(Collectors.toList());
        existingProduct.setSizeColorProductsEntity(sizeColorProductEntities);

        // Lưu cập nhật vào cơ sở dữ liệu
        ProductEntity updatedProduct = iProductRepository.save(existingProduct);

        // Trả về ProductDto tương ứng với Product đã cập nhật
        return iProductMapper.mapToProductDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        // Kiểm tra xem sản phẩm có tồn tại trong cơ sở dữ liệu không
        ProductEntity existingProduct = iProductRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product is not exist with given id: " + productId));
        existingProduct.setIsDelete("true");
        iProductRepository.save(existingProduct);
    }
}
