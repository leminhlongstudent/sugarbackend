package site.sugarnest.backend.service.product;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import site.sugarnest.backend.dto.dto.ProductDto;
import site.sugarnest.backend.dto.dto.ProductFilterDto;

import java.util.List;

public interface IProductService {
    ProductDto createProduct(ProductDto productDto);

    Page<ProductDto> getAllProduct(Pageable pageable);

    List<ProductDto> getProductByAdmin();

    List<ProductDto> findProductByCategoryId(Long categoryId, int limit);

    ProductDto getProductById(Long productId);

    ProductDto updateProduct(Long productId, ProductDto updateProduct);

    void deleteProduct(Long productId);

    Page<ProductDto> getAllProduct(Pageable pageable, ProductFilterDto filter);

    List<ProductDto> findTopSellingProducts(int limit);

    List<ProductDto> findLatestProducts(int limit);

    List<ProductDto> findMostViewedProducts(int limit);

    List<ProductDto> findRecommendedProducts(Long categoryId, int limit);
    Long getTotalProducts();

    Page<ProductDto> searchProduct(String nameProduct, int page, int size);

}

