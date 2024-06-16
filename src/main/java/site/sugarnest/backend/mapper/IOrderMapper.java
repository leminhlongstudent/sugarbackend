package site.sugarnest.backend.mapper;

import org.mapstruct.Mapper;
import site.sugarnest.backend.dto.response.OrderResponse;
import site.sugarnest.backend.entities.OrderEntity;

@Mapper(componentModel = "spring")
public interface IOrderMapper {
    OrderResponse toOrderEntity(OrderEntity orderEntity);
}
