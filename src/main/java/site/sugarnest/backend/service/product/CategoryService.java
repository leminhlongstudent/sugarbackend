package site.sugarnest.backend.service.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import site.sugarnest.backend.dto.dto.CategoryDto;
import site.sugarnest.backend.entities.CategoryEntity;
import site.sugarnest.backend.mapper.ICategoryMapper;
import site.sugarnest.backend.reponsitoties.ICategoryRepository;

import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class CategoryService implements ICategoryService {
    ICategoryRepository iCategoryRepository;
    ICategoryMapper iCategoryMapper;
    @Override
    public List<CategoryDto> getAllCategory() {
        List<CategoryEntity> categoryEntityList = iCategoryRepository.findAll();
        return categoryEntityList.stream().map(iCategoryMapper::mapToCategoryDto).collect(Collectors.toList());
    }
}
