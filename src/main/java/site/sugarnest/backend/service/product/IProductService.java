package site.sugarnest.backend.service.product;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import site.sugarnest.backend.dto.dto.ProductDto;

import java.util.List;

public interface IProductService {
    ProductDto createProduct(ProductDto productDto);

    Page<ProductDto> getAllProduct(Pageable pageable);

    List<ProductDto> findProductByCategoryId(Long categoryId, int limit);

    ProductDto getProductById(Long productId);

    ProductDto updateProduct(Long productId, ProductDto updateProduct);

    void deleteProduct(Long productId);
}

