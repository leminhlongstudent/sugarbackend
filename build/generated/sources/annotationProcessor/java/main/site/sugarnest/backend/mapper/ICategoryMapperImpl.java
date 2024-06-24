package site.sugarnest.backend.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.sugarnest.backend.dto.dto.CategoryDto;
import site.sugarnest.backend.entities.CategoryEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-25T03:40:20+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class ICategoryMapperImpl implements ICategoryMapper {

    @Override
    public CategoryDto mapToCategoryDto(CategoryEntity category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId( category.getId() );
        categoryDto.setNameCategory( category.getNameCategory() );
        categoryDto.setImageCategory( category.getImageCategory() );

        return categoryDto;
    }

    @Override
    public CategoryEntity mapToCategory(CategoryDto category) {
        if ( category == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setId( category.getId() );
        categoryEntity.setNameCategory( category.getNameCategory() );
        categoryEntity.setImageCategory( category.getImageCategory() );

        return categoryEntity;
    }
}
