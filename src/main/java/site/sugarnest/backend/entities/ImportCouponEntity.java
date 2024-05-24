package site.sugarnest.backend.entities;
import lombok.Data;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "import_coupons")
@Data
public class ImportCouponEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_supplier")
    private SupplierEntity supplierEntity;

    @Column(columnDefinition = "TIMESTAMP")
    private Date date;

    private String status;

    @ManyToOne
    @JoinColumn(name = "id_producer")
    private ProducerEntity producerEntity;

    @Column(columnDefinition = "TIMESTAMP")
    private Date createAt;

    @Column(columnDefinition = "TIMESTAMP")
    private Date updateAt;

    @Column(columnDefinition = "TIMESTAMP")
    private Date deleteAt;
}
