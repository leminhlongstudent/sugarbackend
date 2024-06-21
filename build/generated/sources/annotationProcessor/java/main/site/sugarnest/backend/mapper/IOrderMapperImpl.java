package site.sugarnest.backend.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.sugarnest.backend.dto.response.OrderResponse;
import site.sugarnest.backend.entities.OrderDetailEntity;
import site.sugarnest.backend.entities.OrderEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-21T17:42:05+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class IOrderMapperImpl implements IOrderMapper {

    @Override
    public OrderResponse toOrderEntity(OrderEntity orderEntity) {
        if ( orderEntity == null ) {
            return null;
        }

        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setId( orderEntity.getId() );
        orderResponse.setCreateAt( orderEntity.getCreateAt() );
        orderResponse.setDeliveryAt( orderEntity.getDeliveryAt() );
        orderResponse.setStatusPay( orderEntity.getStatusPay() );
        orderResponse.setSale( orderEntity.getSale() );
        orderResponse.setFree_ship( orderEntity.getFree_ship() );
        orderResponse.setTotalPrice( orderEntity.getTotalPrice() );
        orderResponse.setStatus( orderEntity.getStatus() );
        orderResponse.setAddress( orderEntity.getAddress() );
        List<OrderDetailEntity> list = orderEntity.getOrderItems();
        if ( list != null ) {
            orderResponse.setOrderItems( new ArrayList<OrderDetailEntity>( list ) );
        }
        orderResponse.setAccountEntity( orderEntity.getAccountEntity() );
        orderResponse.setNote( orderEntity.getNote() );
        orderResponse.setUpdateAt( orderEntity.getUpdateAt() );

        return orderResponse;
    }
}
