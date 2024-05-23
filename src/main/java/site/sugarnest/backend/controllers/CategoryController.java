package site.sugarnest.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.sugarnest.backend.dto.dto.ApiResponse;
import site.sugarnest.backend.service.product.ICategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private ICategoryService iCategoryService;

    @GetMapping
    public ApiResponse<?> getAllCategory() {
        return ApiResponse.<Object>builder()
                .message("Success")
                .result(iCategoryService.getAllCategory())
                .build();
    }

}
