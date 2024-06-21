package site.sugarnest.backend.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.sugarnest.backend.dto.response.CartItemResponse;
import site.sugarnest.backend.dto.response.CartItemResponse.CartItemResponseBuilder;
import site.sugarnest.backend.entities.CartItemEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-22T00:24:25+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class ICartMapperImpl implements ICartMapper {

    @Override
    public CartItemResponse mapToCartItemDto(CartItemEntity cartItemEntity) {
        if ( cartItemEntity == null ) {
            return null;
        }

        CartItemResponseBuilder cartItemResponse = CartItemResponse.builder();

        if ( cartItemEntity.getQuantity() != null ) {
            cartItemResponse.quantity( cartItemEntity.getQuantity() );
        }
        cartItemResponse.productSize( cartItemEntity.getProductSize() );
        cartItemResponse.productColor( cartItemEntity.getProductColor() );

        return cartItemResponse.build();
    }

    @Override
    public CartItemEntity mapToCartItemEntity(CartItemResponse cartItemDto) {
        if ( cartItemDto == null ) {
            return null;
        }

        CartItemEntity cartItemEntity = new CartItemEntity();

        cartItemEntity.setQuantity( cartItemDto.getQuantity() );
        cartItemEntity.setProductSize( cartItemDto.getProductSize() );
        cartItemEntity.setProductColor( cartItemDto.getProductColor() );

        return cartItemEntity;
    }
}
