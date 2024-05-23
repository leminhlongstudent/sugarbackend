package site.sugarnest.backend.reponsitoties;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.sugarnest.backend.entities.ProductEntity;

import java.util.List;


@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
    Page<ProductEntity> findAll(Pageable pageable);

    @Query("SELECT p FROM ProductEntity p WHERE p.categoryEntity.id = ?1")
    List<ProductEntity> findProductByCategoryId(Long categoryId, Pageable pageable);
}