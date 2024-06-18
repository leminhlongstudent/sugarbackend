package site.sugarnest.backend.mapper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.sugarnest.backend.dto.dto.SendEmailDto;
import site.sugarnest.backend.dto.request.AccountRequest;
import site.sugarnest.backend.dto.response.AccountResponse;
import site.sugarnest.backend.dto.response.RoleResponse;
import site.sugarnest.backend.dto.response.RoleResponse.RoleResponseBuilder;
import site.sugarnest.backend.entities.AccountEntity;
import site.sugarnest.backend.entities.AccountEntity.AccountEntityBuilder;
import site.sugarnest.backend.entities.PermissionEntity;
import site.sugarnest.backend.entities.RoleEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-18T23:06:45+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class IAccountMapperImpl implements IAccountMapper {

    @Override
    public AccountResponse mapToAccountDto(AccountEntity accountEntity) {
        if ( accountEntity == null ) {
            return null;
        }

        AccountResponse accountResponse = new AccountResponse();

        accountResponse.setId( accountEntity.getId() );
        accountResponse.setAccountName( accountEntity.getAccountName() );
        accountResponse.setFullName( accountEntity.getFullName() );
        accountResponse.setBirthday( accountEntity.getBirthday() );
        accountResponse.setAddress( accountEntity.getAddress() );
        accountResponse.setEmail( accountEntity.getEmail() );
        accountResponse.setPhone( accountEntity.getPhone() );
        accountResponse.setIsDelete( accountEntity.getIsDelete() );
        accountResponse.setIsActive( accountEntity.getIsActive() );
        if ( accountEntity.getCreateAt() != null ) {
            accountResponse.setCreateAt( Date.from( accountEntity.getCreateAt().toInstant( ZoneOffset.UTC ) ) );
        }
        accountResponse.setImage( accountEntity.getImage() );
        if ( accountEntity.getUpdateAt() != null ) {
            accountResponse.setUpdateAt( Date.from( accountEntity.getUpdateAt().toInstant( ZoneOffset.UTC ) ) );
        }
        accountResponse.setDeleteAt( accountEntity.getDeleteAt() );
        accountResponse.setIdOther( accountEntity.getIdOther() );
        accountResponse.setCurrentPassword( accountEntity.getCurrentPassword() );
        accountResponse.setNumber_login_fail( accountEntity.getNumber_login_fail() );
        accountResponse.setRoles( roleEntitySetToRoleResponseSet( accountEntity.getRoles() ) );

        return accountResponse;
    }

    @Override
    public AccountEntity mapToAccountEntity(AccountRequest accountDto) {
        if ( accountDto == null ) {
            return null;
        }

        AccountEntityBuilder accountEntity = AccountEntity.builder();

        accountEntity.id( accountDto.getId() );
        accountEntity.accountName( accountDto.getAccountName() );
        accountEntity.password( accountDto.getPassword() );
        accountEntity.fullName( accountDto.getFullName() );
        accountEntity.birthday( accountDto.getBirthday() );
        accountEntity.address( accountDto.getAddress() );
        accountEntity.email( accountDto.getEmail() );
        accountEntity.phone( accountDto.getPhone() );
        accountEntity.isDelete( accountDto.getIsDelete() );
        accountEntity.isActive( accountDto.getIsActive() );
        if ( accountDto.getCreateAt() != null ) {
            accountEntity.createAt( LocalDateTime.ofInstant( accountDto.getCreateAt().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        accountEntity.image( accountDto.getImage() );
        if ( accountDto.getUpdateAt() != null ) {
            accountEntity.updateAt( LocalDateTime.ofInstant( accountDto.getUpdateAt().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        accountEntity.deleteAt( accountDto.getDeleteAt() );
        accountEntity.idOther( accountDto.getIdOther() );
        accountEntity.currentPassword( accountDto.getCurrentPassword() );
        accountEntity.number_login_fail( accountDto.getNumber_login_fail() );

        return accountEntity.build();
    }

    @Override
    public AccountEntity mapToEmailDto(SendEmailDto sendEmailDto) {
        if ( sendEmailDto == null ) {
            return null;
        }

        AccountEntityBuilder accountEntity = AccountEntity.builder();

        accountEntity.email( sendEmailDto.getEmail() );
        accountEntity.verificationCode( sendEmailDto.getVerificationCode() );

        return accountEntity.build();
    }

    protected RoleResponse roleEntityToRoleResponse(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RoleResponseBuilder roleResponse = RoleResponse.builder();

        roleResponse.name( roleEntity.getName() );
        roleResponse.description( roleEntity.getDescription() );
        Set<PermissionEntity> set = roleEntity.getPermissions();
        if ( set != null ) {
            roleResponse.permissions( new HashSet<PermissionEntity>( set ) );
        }

        return roleResponse.build();
    }

    protected Set<RoleResponse> roleEntitySetToRoleResponseSet(Set<RoleEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleResponse> set1 = new HashSet<RoleResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleEntity roleEntity : set ) {
            set1.add( roleEntityToRoleResponse( roleEntity ) );
        }

        return set1;
    }
}
