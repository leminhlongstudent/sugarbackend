package site.sugarnest.backend.dto.dto;
import lombok.Data;
import site.sugarnest.backend.entities.SubCategoryEntity;

import java.util.List;
@Data
public class CategoryDto {
    private Long id;
    private String nameCategory;
    private String imageCategory;
    private List<SubCategoryEntity> subCategoryEntity;
}
