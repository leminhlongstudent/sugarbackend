package site.sugarnest.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameProduct;

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_supplier", nullable = false)
    private SupplierEntity supplierEntity;

    @ManyToOne
    @JoinColumn(name = "id_producer", nullable = false)
    private ProducerEntity producerEntity;

    @ManyToOne
    @JoinColumn(name = "id_categorie", nullable = false)
    private CategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name = "id_sub_categorie", nullable = false)
    private SubCategoryEntity subCategoryEntity;

    private String isActive;

    private String isDelete;

    private String status;

    @OneToOne(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private ProductPriceEntity productPriceEntity;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private List<ImageProductEntity> imageProducts;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SizeColorProductEntity> sizeColorProductsEntity;

    @OneToMany(mappedBy = "productEntity")
    @JsonIgnore
    private List<RateEntity> ratesEntity;

    @OneToMany(mappedBy = "productEntity")
    @JsonIgnore
    private List<ImportCouponDetailEntity> importCouponDetailsEntity;

    @OneToMany(mappedBy = "productEntity")
    @JsonIgnore
    private List<OrderDetailEntity> orderDetailsEntity;
}
