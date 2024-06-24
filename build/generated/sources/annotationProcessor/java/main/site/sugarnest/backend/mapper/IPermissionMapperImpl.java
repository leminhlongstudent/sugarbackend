package site.sugarnest.backend.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.sugarnest.backend.dto.request.PermissionRequest;
import site.sugarnest.backend.dto.response.PermissionResponse;
import site.sugarnest.backend.dto.response.PermissionResponse.PermissionResponseBuilder;
import site.sugarnest.backend.entities.PermissionEntity;
import site.sugarnest.backend.entities.PermissionEntity.PermissionEntityBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-25T02:52:13+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class IPermissionMapperImpl implements IPermissionMapper {

    @Override
    public PermissionEntity toPermission(PermissionRequest request) {
        if ( request == null ) {
            return null;
        }

        PermissionEntityBuilder permissionEntity = PermissionEntity.builder();

        permissionEntity.name( request.getName() );
        permissionEntity.description( request.getDescription() );

        return permissionEntity.build();
    }

    @Override
    public PermissionResponse toPermissionResponse(PermissionEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PermissionResponseBuilder permissionResponse = PermissionResponse.builder();

        permissionResponse.name( entity.getName() );
        permissionResponse.description( entity.getDescription() );

        return permissionResponse.build();
    }
}
