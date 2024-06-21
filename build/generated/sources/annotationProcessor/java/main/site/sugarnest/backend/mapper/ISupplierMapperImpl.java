package site.sugarnest.backend.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.sugarnest.backend.dto.dto.SupplierDto;
import site.sugarnest.backend.dto.dto.SupplierDto.SupplierDtoBuilder;
import site.sugarnest.backend.entities.SupplierEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-21T18:01:47+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class ISupplierMapperImpl implements ISupplierMapper {

    @Override
    public SupplierDto mapToSupplierDto(SupplierEntity supplierEntity) {
        if ( supplierEntity == null ) {
            return null;
        }

        SupplierDtoBuilder supplierDto = SupplierDto.builder();

        supplierDto.id( supplierEntity.getId() );
        supplierDto.nameSupplier( supplierEntity.getNameSupplier() );
        supplierDto.isActive( supplierEntity.getIsActive() );

        return supplierDto.build();
    }

    @Override
    public SupplierEntity mapToSupplier(SupplierDto supplierDto) {
        if ( supplierDto == null ) {
            return null;
        }

        SupplierEntity supplierEntity = new SupplierEntity();

        supplierEntity.setId( supplierDto.getId() );
        supplierEntity.setNameSupplier( supplierDto.getNameSupplier() );
        supplierEntity.setIsActive( supplierDto.getIsActive() );

        return supplierEntity;
    }
}
