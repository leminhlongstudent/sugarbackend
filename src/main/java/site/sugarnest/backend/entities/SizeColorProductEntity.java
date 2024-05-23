package site.sugarnest.backend.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import jakarta.persistence.*;
@Entity
@Table(name = "size_color_products")
@Data
public class SizeColorProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", nullable = false)
    @JsonBackReference
    private ProductEntity productEntity;

    private String size;

    private String color;
}
