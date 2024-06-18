package site.sugarnest.backend.reponsitoties;

import org.springframework.data.jpa.repository.JpaRepository;
import site.sugarnest.backend.entities.ProductEntity;
import site.sugarnest.backend.entities.SizeColorProductEntity;

import java.util.List;
import java.util.Optional;

public interface ISizeColorProductRepository extends JpaRepository<SizeColorProductEntity, Long> {
    void deleteByProductEntity(ProductEntity productEntity);
    SizeColorProductEntity findByProductEntityAndSizeAndColor(ProductEntity product, String size, String color);

    SizeColorProductEntity findByProductEntityIdAndSizeAndColor(Long productId, String size, String color);
}
