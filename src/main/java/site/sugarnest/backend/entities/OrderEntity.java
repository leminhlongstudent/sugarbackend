package site.sugarnest.backend.entities;
import lombok.Data;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "orders")
@Data
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TIMESTAMP")
    private Date createAt;

    @Column(columnDefinition = "TIMESTAMP")
    private Date deliveryAt;

    private String statusPay;

    @ManyToOne
    @JoinColumn(name = "id_account", nullable = false)
    private AccountEntity accountEntity;

    private String sale;

    private Double free_ship;

    private Double totalPrice;

    private String status;

    private String address;

    private Integer wardId;

    private Integer districtId;

    @Column(columnDefinition = "longtext")
    private String note;

    @Column(columnDefinition = "TIMESTAMP")
    private Date updateAt;
}
