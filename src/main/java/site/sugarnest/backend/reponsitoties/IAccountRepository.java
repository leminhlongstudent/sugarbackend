package site.sugarnest.backend.reponsitoties;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.sugarnest.backend.entities.AccountEntity;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByEmail(String email);

    Optional<AccountEntity> findByPhone(String phone);

    Optional<AccountEntity> findByVerificationCode(String verificationCode);

    Optional<AccountEntity> findByAccountName(String accountName);

    @Query("SELECT a FROM AccountEntity a LEFT JOIN FETCH a.roles r LEFT JOIN FETCH r.permissions WHERE a.email = :email")
    AccountEntity findByEmailWithRolesAndPermissions(@Param("email") String email);

}
