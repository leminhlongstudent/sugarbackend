package site.sugarnest.backend.service.product;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import site.sugarnest.backend.exception.AppException;
import site.sugarnest.backend.exception.ErrorCode;
import site.sugarnest.backend.util.ProductSpecification;
import site.sugarnest.backend.dto.dto.ProductDto;
import site.sugarnest.backend.dto.dto.ProductFilterDto;
import site.sugarnest.backend.entities.ImageProductEntity;

import site.sugarnest.backend.entities.*;
import site.sugarnest.backend.exception.ResourceNotFoundException;
import site.sugarnest.backend.mapper.IProductMapper;
import site.sugarnest.backend.reponsitoties.IImageProductRepository;
import site.sugarnest.backend.reponsitoties.IInventoryRepository;
import site.sugarnest.backend.reponsitoties.IProductRepository;
import site.sugarnest.backend.reponsitoties.ISizeColorProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductService implements IProductService {
    private IProductRepository iProductRepository;
    private IProductMapper iProductMapper;
    private ISizeColorProductRepository sizeColorProductRepository;
    private IInventoryRepository inventoryRepository;
    private IImageProductRepository imageProductRepository;
    private ISizeColorProductRepository iSizeColorProductRepository;
    private IImageProductRepository iImageProductRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        ProductEntity productEntity = iProductMapper.mapToProduct(productDto);

        List<ImageProductEntity> imageProductEntities = productDto.getImageProductEntity().stream()
                .map(imageDto -> {
                    ImageProductEntity imageProductEntity = iProductMapper.mapToImageProduct(imageDto);
                    imageProductEntity.setProductEntity(productEntity);
                    return imageProductEntity;
                }).collect(Collectors.toList());

        List<SizeColorProductEntity> sizeColorProducts = productDto.getSizeColorProductsEntity().stream()
                .map(sizeColorProductDto -> {
                    SizeColorProductEntity sizeColorProductEntity = iProductMapper.mapToSizeColorProduct(sizeColorProductDto);
                    sizeColorProductEntity.setProductEntity(productEntity);

                    InventoryEntity inventoryEntity = new InventoryEntity();
                    inventoryEntity.setSizeColorProductEntity(sizeColorProductEntity);
                    inventoryEntity.setQuantity(sizeColorProductDto.getInventoryEntity().getQuantity());
                    inventoryEntity.setDateAdd();
                    inventoryEntity.setLastUpdatedDate();
                    sizeColorProductEntity.setInventoryEntity(inventoryEntity);
                    return sizeColorProductEntity;
                }).collect(Collectors.toList());
        productEntity.setImageProductEntity(imageProductEntities);
        productEntity.setSizeColorProductsEntity(sizeColorProducts);
        ProductEntity productSave = iProductRepository.save(productEntity);

        return iProductMapper.mapToProductDto(productSave);
    }


    @Override
    public Page<ProductDto> getAllProduct(Pageable pageable) {
        Page<ProductEntity> products = iProductRepository.findAll(pageable);
        List<ProductDto> productDtos = products.stream().map(product -> iProductMapper.mapToProductDto(product)).collect(Collectors.toList());
        return new PageImpl<>(productDtos, pageable, products.getTotalElements());
    }

    public Page<ProductDto> getAllProduct(Pageable pageable, ProductFilterDto filter) {
        Page<ProductEntity> products = iProductRepository.findAll(ProductSpecification.getSpecifications(filter, pageable), pageable);
        List<ProductDto> productDtos = products.stream()
                .map(product -> iProductMapper.mapToProductDto(product))
                .collect(Collectors.toList());
        return new PageImpl<>(productDtos, pageable, products.getTotalElements());
    }

    @Override
    public List<ProductDto> findTopSellingProducts(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<ProductEntity> products = iProductRepository.findTopSellingProducts(pageable);
         return products.stream().map(product -> iProductMapper.mapToProductDto(product)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findLatestProducts(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<ProductEntity> products = iProductRepository.findLatestProducts(pageable);
        return products.stream().map(product -> iProductMapper.mapToProductDto(product)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findMostViewedProducts(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<ProductEntity> products = iProductRepository.findMostViewedProducts(pageable);
        return products.stream().map(product -> iProductMapper.mapToProductDto(product)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findRecommendedProducts(Long categoryId, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<ProductEntity> products = iProductRepository.findRecommendedProducts(categoryId, pageable);
        return products.stream().map(product -> iProductMapper.mapToProductDto(product)).collect(Collectors.toList());
    }

    @Override
    public Long getTotalProducts() {
        return iProductRepository.count();
    }

    @Override
    public Page<ProductDto> searchProduct(String nameProduct, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ProductEntity> products = iProductRepository.findByNameProductContainingIgnoreCase(nameProduct, pageable).getContent();
        return iProductRepository.findByNameProductContainingIgnoreCase(nameProduct, pageable)
                .map(product -> iProductMapper.mapToProductDto(product));
    }


    @Override
    public List<ProductDto> getProductByAdmin() {
        List<ProductEntity> products = iProductRepository.getProductByAdmin("false");
        return products.stream().map(product -> iProductMapper.mapToProductDto(product)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findProductByCategoryId(Long categoryId, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<ProductEntity> products = iProductRepository.findProductByCategoryId(categoryId, pageable);
        return products.stream().map(product -> iProductMapper.mapToProductDto(product)).collect(Collectors.toList());
    }



    @Override
    public ProductDto getProductById(Long productId) {
        ProductEntity product = iProductRepository.findById(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        product.setViews(product.getViews() + 1);
        iProductRepository.save(product);
        return iProductMapper.mapToProductDto(product);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        Optional<ProductEntity> productEntityOptional = iProductRepository.findById(productId);

        if (productEntityOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id " + productId);
        }

        ProductEntity productEntity = productEntityOptional.get();

        productEntity.setNameProduct(productDto.getNameProduct());
        productEntity.setDescription(productDto.getDescription());
        productEntity.setCategoryEntity(productDto.getCategoryEntity());
        productEntity.setProducerEntity(productDto.getProducerEntity());
        productEntity.setSupplierEntity(productDto.getSupplierEntity());

        iImageProductRepository.deleteByProductEntity(productEntity);

        iSizeColorProductRepository.deleteByProductEntity(productEntity);
        List<ImageProductEntity> imageProductEntities = productDto.getImageProductEntity().stream()
                .map(imageDto -> {
                    ImageProductEntity imageProductEntity = iProductMapper.mapToImageProduct(imageDto);
                    imageProductEntity.setProductEntity(productEntity);
                    return imageProductEntity;
                }).collect(Collectors.toList());

        List<SizeColorProductEntity> sizeColorProducts = productDto.getSizeColorProductsEntity().stream()
                .map(sizeColorProductDto -> {
                    SizeColorProductEntity sizeColorProductEntity = iProductMapper.mapToSizeColorProduct(sizeColorProductDto);
                    sizeColorProductEntity.setProductEntity(productEntity);

                    InventoryEntity inventoryEntity = new InventoryEntity();
                    inventoryEntity.setSizeColorProductEntity(sizeColorProductEntity);
                    inventoryEntity.setQuantity(sizeColorProductDto.getInventoryEntity().getQuantity());
                    inventoryEntity.setDateAdd();
                    inventoryEntity.setLastUpdatedDate();
                    sizeColorProductEntity.setInventoryEntity(inventoryEntity);
                    return sizeColorProductEntity;
                }).collect(Collectors.toList());

        productEntity.setImageProductEntity(imageProductEntities);
        productEntity.setSizeColorProductsEntity(sizeColorProducts);
        ProductEntity updatedProductEntity = iProductRepository.save(productEntity);

        return iProductMapper.mapToProductDto(updatedProductEntity);
    }


    @Override
    public void deleteProduct(Long productId) {
        ProductEntity existingProduct = iProductRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        existingProduct.setIsDelete("true");
        iProductRepository.save(existingProduct);
    }
}
