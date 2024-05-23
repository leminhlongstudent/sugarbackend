package site.sugarnest.backend.entities;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "sub_categories")
@Data
public class SubCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nameSubCategorie;

    @ManyToOne
    @JoinColumn(name = "id_categorie", nullable = false)
    private CategoryEntity categoryEntity;
}
