package site.sugarnest.backend.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.sugarnest.backend.dto.request.PromotionRequest;
import site.sugarnest.backend.dto.response.PromotionResponse;
import site.sugarnest.backend.dto.response.PromotionResponse.PromotionResponseBuilder;
import site.sugarnest.backend.entities.PromotionEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-21T17:42:05+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class IPromotionMapperImpl implements IPromotionMapper {

    @Override
    public PromotionEntity mapToPromotionEntity(PromotionRequest promotionRequest) {
        if ( promotionRequest == null ) {
            return null;
        }

        PromotionEntity promotionEntity = new PromotionEntity();

        promotionEntity.setId( promotionRequest.getId() );
        promotionEntity.setName( promotionRequest.getName() );
        promotionEntity.setDescription( promotionRequest.getDescription() );
        promotionEntity.setCode( promotionRequest.getCode() );
        promotionEntity.setPercentageDiscount( promotionRequest.getPercentageDiscount() );
        promotionEntity.setFixedDiscount( promotionRequest.getFixedDiscount() );
        promotionEntity.setQuantity( promotionRequest.getQuantity() );
        promotionEntity.setStartTime( promotionRequest.getStartTime() );
        promotionEntity.setEndTime( promotionRequest.getEndTime() );
        promotionEntity.setStatus( promotionRequest.getStatus() );
        promotionEntity.setIsDelete( promotionRequest.getIsDelete() );
        promotionEntity.setApplicableCondition( promotionRequest.getApplicableCondition() );
        promotionEntity.setUsageLimit( promotionRequest.getUsageLimit() );
        promotionEntity.setPromotionType( promotionRequest.getPromotionType() );
        promotionEntity.setCreatedBy( promotionRequest.getCreatedBy() );

        return promotionEntity;
    }

    @Override
    public PromotionResponse mapToPromotionDto(PromotionEntity promotionEntity) {
        if ( promotionEntity == null ) {
            return null;
        }

        PromotionResponseBuilder promotionResponse = PromotionResponse.builder();

        promotionResponse.id( promotionEntity.getId() );
        promotionResponse.name( promotionEntity.getName() );
        promotionResponse.description( promotionEntity.getDescription() );
        promotionResponse.code( promotionEntity.getCode() );
        promotionResponse.percentageDiscount( promotionEntity.getPercentageDiscount() );
        promotionResponse.fixedDiscount( promotionEntity.getFixedDiscount() );
        promotionResponse.quantity( promotionEntity.getQuantity() );
        promotionResponse.startTime( promotionEntity.getStartTime() );
        promotionResponse.endTime( promotionEntity.getEndTime() );
        promotionResponse.status( promotionEntity.getStatus() );
        promotionResponse.isDelete( promotionEntity.getIsDelete() );
        promotionResponse.applicableCondition( promotionEntity.getApplicableCondition() );
        promotionResponse.usageLimit( promotionEntity.getUsageLimit() );
        promotionResponse.promotionType( promotionEntity.getPromotionType() );
        promotionResponse.createdBy( promotionEntity.getCreatedBy() );

        return promotionResponse.build();
    }
}
