package site.sugarnest.backend.entities;
import lombok.Data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "inventorys")
@Data
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "id_size_color", nullable = false)
    private SizeColorProductEntity sizeColorProductEntity;

    private Long quantity;

    @Column(nullable = false)
    private Date dateAdd;

    @Column(nullable = false)
    private Date lastUpdatedDate;
}

