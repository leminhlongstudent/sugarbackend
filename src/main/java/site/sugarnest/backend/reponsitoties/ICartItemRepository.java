package site.sugarnest.backend.reponsitoties;

import org.springframework.data.jpa.repository.JpaRepository;
import site.sugarnest.backend.entities.CartItemEntity;

import java.util.List;

public interface ICartItemRepository extends JpaRepository<CartItemEntity, Integer> {
    List<CartItemEntity> findByCartEntityId(Integer cartId);
}