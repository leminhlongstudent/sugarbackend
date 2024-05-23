package site.sugarnest.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.sugarnest.backend.dto.dto.ApiResponse;
import site.sugarnest.backend.dto.dto.ProductDto;
import site.sugarnest.backend.service.product.IProductService;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private IProductService iProductService;

    @PostMapping
    public ApiResponse<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto saveProduct = iProductService.createProduct(productDto);
        return ApiResponse.<ProductDto>builder()
                .message("Product created!")
                .result(saveProduct)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductDto> getProductById(@PathVariable("id") Long id) {
        ProductDto productDto = iProductService.getProductById(id);
        return ApiResponse.<ProductDto>builder()
                .message("Success")
                .result(productDto)
                .build();
    }

    @GetMapping
    public ApiResponse<?> getAllProduct(@PageableDefault(size = 12) Pageable pageable) {
        int page = pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1;
        Pageable customPageable = PageRequest.of(page, pageable.getPageSize(), pageable.getSort());
        Page<ProductDto> listProductDto = iProductService.getAllProduct(customPageable);
        int totalPages = listProductDto.getTotalPages();
        return ApiResponse.<Object>builder()
                .message("Success")
                .result(Map.of("content", listProductDto.getContent(), "totalPages", totalPages))
                .build();
    }

    @GetMapping("/category/{id}/limit/{limit}")
    public ApiResponse<List<ProductDto>> findProductByCategoryId(@PathVariable("id") Long categoryId, @PathVariable("limit") int limit) {
        List<ProductDto> productDtos = iProductService.findProductByCategoryId(categoryId, limit);
        return ApiResponse.<List<ProductDto>>builder()
                .message("Success")
                .result(productDtos)
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<ProductDto> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto updateProduct) {
        ProductDto productDto = iProductService.updateProduct(id, updateProduct);
        return ApiResponse.<ProductDto>builder()
                .message("Product updated!")
                .result(productDto)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteProduct(@PathVariable("id") Long id) {
        iProductService.deleteProduct(id);
        return ApiResponse.<String>builder()
                .message("Product deleted!")
                .build();
    }

}
