package site.sugarnest.backend.dto.dto;

import lombok.*;
import site.sugarnest.backend.entities.*;

import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String nameProduct;
    private String description;
    private SupplierEntity supplierEntity;
    private ProducerEntity producerEntity;
    private CategoryEntity categoryEntity;
    private SubCategoryEntity subCategoryEntity;
    private String isActive;
    private String isDelete;
    private String status;
    private ProductPriceEntity productPriceEntity;
    private List<ImageProductEntity> imageProducts;
    private List<SizeColorProductEntity> sizeColorProductsEntity;
    private List<RateEntity> ratesEntity;
    private List<ImportCouponDetailEntity> importCouponDetailsEntity;
    private List<OrderDetailEntity> orderDetailsEntity;
}

