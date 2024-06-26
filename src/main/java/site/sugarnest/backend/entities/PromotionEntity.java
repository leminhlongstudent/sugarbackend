package site.sugarnest.backend.entities;
import lombok.Data;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "promotions")
@Data
public class PromotionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String code;

    private Double percentageDiscount; // Giảm giá theo phần trăm

    private Double fixedDiscount; // Giảm giá cố định

    private Integer quantity;

    private Date startTime;

    private Date endTime;

    private Integer status;

    private Integer isDelete;

    private String applicableCondition; // Điều kiện áp dụng

    private Integer usageLimit; // Giới hạn số lần sử dụng

    private String promotionType; // Loại khuyến mãi (giảm giá, tặng quà, ...)

    private String createdBy;

    private String updatedBy;

    private Date createdAt;

    private Date updatedAt;
}