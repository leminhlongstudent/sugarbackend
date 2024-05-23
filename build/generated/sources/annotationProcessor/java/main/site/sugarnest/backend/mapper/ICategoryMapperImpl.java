package site.sugarnest.backend.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.sugarnest.backend.dto.dto.CategoryDto;
import site.sugarnest.backend.entities.CategoryEntity;
import site.sugarnest.backend.entities.SubCategoryEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-24T02:04:48+0700",
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
        List<SubCategoryEntity> list = category.getSubCategoryEntity();
        if ( list != null ) {
            categoryDto.setSubCategoryEntity( new ArrayList<SubCategoryEntity>( list ) );
        }

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
        List<SubCategoryEntity> list = category.getSubCategoryEntity();
        if ( list != null ) {
            categoryEntity.setSubCategoryEntity( new ArrayList<SubCategoryEntity>( list ) );
        }

        return categoryEntity;
    }
}
