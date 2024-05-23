package site.sugarnest.backend.entities;
import lombok.Data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "discounts")
@Data
public class DiscountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameDiscount;

    @Column(columnDefinition = "longtext")
    private String description;

    @Column(columnDefinition = "mediumtext")
    private String codeDiscount;

    private Double percentage;

    private Integer quantity;

    @Column(nullable = false, columnDefinition = "datetime default current_timestamp")
    private Date startTime;

    private Date endTime;

    @Column(name = "condition_text")
    private String condition;

    private Integer status;

    @Column(nullable = false, columnDefinition = "datetime default current_timestamp")
    private Date createAt;

    private Date updateAt;

    private Integer isDelete;
}