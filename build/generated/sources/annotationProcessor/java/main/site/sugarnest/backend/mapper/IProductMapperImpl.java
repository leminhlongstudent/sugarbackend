package site.sugarnest.backend.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.sugarnest.backend.dto.dto.ProductDto;
import site.sugarnest.backend.entities.ImageProductEntity;
import site.sugarnest.backend.entities.ImportCouponDetailEntity;
import site.sugarnest.backend.entities.OrderDetailEntity;
import site.sugarnest.backend.entities.ProductEntity;
import site.sugarnest.backend.entities.RateEntity;
import site.sugarnest.backend.entities.SizeColorProductEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-21T18:01:47+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class IProductMapperImpl implements IProductMapper {

    @Override
    public ProductDto mapToProductDto(ProductEntity product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( product.getId() );
        productDto.setNameProduct( product.getNameProduct() );
        productDto.setDescription( product.getDescription() );
        productDto.setSupplierEntity( product.getSupplierEntity() );
        productDto.setProducerEntity( product.getProducerEntity() );
        productDto.setCategoryEntity( product.getCategoryEntity() );
        productDto.setIsActive( product.getIsActive() );
        productDto.setIsDelete( product.getIsDelete() );
        productDto.setStatus( product.getStatus() );
        List<ImageProductEntity> list = product.getImageProductEntity();
        if ( list != null ) {
            productDto.setImageProductEntity( new ArrayList<ImageProductEntity>( list ) );
        }
        List<SizeColorProductEntity> list1 = product.getSizeColorProductsEntity();
        if ( list1 != null ) {
            productDto.setSizeColorProductsEntity( new ArrayList<SizeColorProductEntity>( list1 ) );
        }
        List<RateEntity> list2 = product.getRatesEntity();
        if ( list2 != null ) {
            productDto.setRatesEntity( new ArrayList<RateEntity>( list2 ) );
        }
        List<ImportCouponDetailEntity> list3 = product.getImportCouponDetailsEntity();
        if ( list3 != null ) {
            productDto.setImportCouponDetailsEntity( new ArrayList<ImportCouponDetailEntity>( list3 ) );
        }
        List<OrderDetailEntity> list4 = product.getOrderDetailsEntity();
        if ( list4 != null ) {
            productDto.setOrderDetailsEntity( new ArrayList<OrderDetailEntity>( list4 ) );
        }

        return productDto;
    }

    @Override
    public ProductEntity mapToProduct(ProductDto product) {
        if ( product == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setId( product.getId() );
        productEntity.setNameProduct( product.getNameProduct() );
        productEntity.setDescription( product.getDescription() );
        productEntity.setSupplierEntity( product.getSupplierEntity() );
        productEntity.setProducerEntity( product.getProducerEntity() );
        productEntity.setCategoryEntity( product.getCategoryEntity() );
        productEntity.setIsActive( product.getIsActive() );
        productEntity.setIsDelete( product.getIsDelete() );
        productEntity.setStatus( product.getStatus() );
        List<ImageProductEntity> list = product.getImageProductEntity();
        if ( list != null ) {
            productEntity.setImageProductEntity( new ArrayList<ImageProductEntity>( list ) );
        }
        List<SizeColorProductEntity> list1 = product.getSizeColorProductsEntity();
        if ( list1 != null ) {
            productEntity.setSizeColorProductsEntity( new ArrayList<SizeColorProductEntity>( list1 ) );
        }
        List<RateEntity> list2 = product.getRatesEntity();
        if ( list2 != null ) {
            productEntity.setRatesEntity( new ArrayList<RateEntity>( list2 ) );
        }
        List<ImportCouponDetailEntity> list3 = product.getImportCouponDetailsEntity();
        if ( list3 != null ) {
            productEntity.setImportCouponDetailsEntity( new ArrayList<ImportCouponDetailEntity>( list3 ) );
        }
        List<OrderDetailEntity> list4 = product.getOrderDetailsEntity();
        if ( list4 != null ) {
            productEntity.setOrderDetailsEntity( new ArrayList<OrderDetailEntity>( list4 ) );
        }

        return productEntity;
    }

    @Override
    public ImageProductEntity mapToImageProduct(ImageProductEntity imageProductDto) {
        if ( imageProductDto == null ) {
            return null;
        }

        ImageProductEntity imageProductEntity = new ImageProductEntity();

        imageProductEntity.setId( imageProductDto.getId() );
        imageProductEntity.setProductEntity( imageProductDto.getProductEntity() );
        imageProductEntity.setImage( imageProductDto.getImage() );

        return imageProductEntity;
    }

    @Override
    public SizeColorProductEntity mapToSizeColorProduct(SizeColorProductEntity SizeColorProductDto) {
        if ( SizeColorProductDto == null ) {
            return null;
        }

        SizeColorProductEntity sizeColorProductEntity = new SizeColorProductEntity();

        sizeColorProductEntity.setId( SizeColorProductDto.getId() );
        sizeColorProductEntity.setProductEntity( SizeColorProductDto.getProductEntity() );
        sizeColorProductEntity.setInventoryEntity( SizeColorProductDto.getInventoryEntity() );
        sizeColorProductEntity.setSize( SizeColorProductDto.getSize() );
        sizeColorProductEntity.setColor( SizeColorProductDto.getColor() );
        sizeColorProductEntity.setListPrice( SizeColorProductDto.getListPrice() );
        sizeColorProductEntity.setDiscount( SizeColorProductDto.getDiscount() );
        sizeColorProductEntity.setDiscountPrice( SizeColorProductDto.getDiscountPrice() );

        return sizeColorProductEntity;
    }
}
