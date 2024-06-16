package site.sugarnest.backend.reponsitoties;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.sugarnest.backend.entities.AccountEntity;
import site.sugarnest.backend.entities.OrderEntity;
import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findByAccountEntity(AccountEntity accountEntity);
}

