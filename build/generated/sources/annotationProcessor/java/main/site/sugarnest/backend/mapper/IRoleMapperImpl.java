package site.sugarnest.backend.mapper;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.sugarnest.backend.dto.request.RoleRequest;
import site.sugarnest.backend.dto.response.RoleResponse;
import site.sugarnest.backend.dto.response.RoleResponse.RoleResponseBuilder;
import site.sugarnest.backend.entities.PermissionEntity;
import site.sugarnest.backend.entities.RoleEntity;
import site.sugarnest.backend.entities.RoleEntity.RoleEntityBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-21T17:42:05+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class IRoleMapperImpl implements IRoleMapper {

    @Override
    public RoleEntity toRole(RoleRequest request) {
        if ( request == null ) {
            return null;
        }

        RoleEntityBuilder roleEntity = RoleEntity.builder();

        roleEntity.name( request.getName() );
        roleEntity.description( request.getDescription() );

        return roleEntity.build();
    }

    @Override
    public RoleResponse toRoleResponse(RoleEntity entity) {
        if ( entity == null ) {
            return null;
        }

        RoleResponseBuilder roleResponse = RoleResponse.builder();

        roleResponse.name( entity.getName() );
        roleResponse.description( entity.getDescription() );
        Set<PermissionEntity> set = entity.getPermissions();
        if ( set != null ) {
            roleResponse.permissions( new HashSet<PermissionEntity>( set ) );
        }

        return roleResponse.build();
    }
}
