package site.sugarnest.backend.service.product;

import site.sugarnest.backend.dto.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {
    List<CategoryDto> getAllCategory();
}
