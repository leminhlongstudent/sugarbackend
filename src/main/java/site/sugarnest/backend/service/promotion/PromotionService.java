package site.sugarnest.backend.service.promotion;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.sugarnest.backend.dto.request.PromotionRequest;
import site.sugarnest.backend.dto.response.PromotionResponse;
import site.sugarnest.backend.entities.AccountEntity;
import site.sugarnest.backend.entities.ProductEntity;
import site.sugarnest.backend.entities.PromotionEntity;
import site.sugarnest.backend.exception.AppException;
import site.sugarnest.backend.exception.ErrorCode;
import site.sugarnest.backend.mapper.IPromotionMapper;
import site.sugarnest.backend.reponsitoties.IAccountRepository;
import site.sugarnest.backend.reponsitoties.IProductRepository;
import site.sugarnest.backend.reponsitoties.IPromotionRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class PromotionService {

    private IPromotionRepository promotionRepository;
    private IPromotionMapper promotionMapper;
    private IProductRepository productRepository;
    private IAccountRepository accountRepository;

    public PromotionResponse createPromotion(PromotionRequest promotionRequest) {
        return promotionMapper.mapToPromotionDto(promotionRepository.save(promotionMapper.mapToPromotionEntity(promotionRequest)));
    }
    public List<PromotionResponse> getAllPromotions() {
        List<PromotionEntity> promotionEntities = promotionRepository.findAll();
        return promotionEntities.stream().map(promotionMapper::mapToPromotionDto).toList();
    }

    public PromotionResponse getPromotionById(Long id) {
        return promotionMapper.mapToPromotionDto(promotionRepository.findById(id).orElse(null));
    }

    public PromotionResponse updatePromotion(PromotionRequest promotionRequest) {

        PromotionEntity promotionEntity = promotionRepository.findById(promotionRequest.getId()).orElse(null);
        if (promotionEntity == null) {
            throw new AppException(ErrorCode.PROMOTION_NOT_FOUND);
        }
        if (promotionRequest.getEndTime().before(promotionRequest.getStartTime())) {
            throw new AppException(ErrorCode.INVALID_TIME);
        }
        promotionEntity.setName(promotionRequest.getName());
        promotionEntity.setDescription(promotionRequest.getDescription());
        promotionEntity.setCode(promotionRequest.getCode());
        promotionEntity.setPercentageDiscount(promotionRequest.getPercentageDiscount());
        promotionEntity.setFixedDiscount(promotionRequest.getFixedDiscount());
        promotionEntity.setQuantity(promotionRequest.getQuantity());
        promotionEntity.setStartTime(promotionRequest.getStartTime());
        promotionEntity.setEndTime(promotionRequest.getEndTime());
        promotionEntity.setStatus(promotionRequest.getStatus());
        promotionEntity.setIsDelete(promotionRequest.getIsDelete());
        promotionEntity.setApplicableCondition(promotionRequest.getApplicableCondition());
        promotionEntity.setUsageLimit(promotionRequest.getUsageLimit());
        promotionEntity.setPromotionType(promotionRequest.getPromotionType());
        promotionEntity.setCreatedBy(promotionRequest.getCreatedBy());
        return promotionMapper.mapToPromotionDto(promotionRepository.save(promotionEntity));

    }

    public void deletePromotion(Long id) {
        promotionRepository.deleteById(id);
    }

    public PromotionResponse getPromotionByCode(String code) {
        return promotionMapper.mapToPromotionDto(promotionRepository.findByCode(code));
    }

    public void addProductToPromotion(Long promotionId, Long productId) {
        Optional<PromotionEntity> promotion = promotionRepository.findById(promotionId);
        Optional<ProductEntity> product = productRepository.findById(productId);

        if (promotion.isPresent() && product.isPresent()) {
            PromotionEntity promotionEntity = promotion.get();
            ProductEntity productEntity = product.get();
            promotionRepository.save(promotionEntity);
        } else {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
    }

    public void addAccountToPromotion(Long promotionId, Long accountId) {
        Optional<PromotionEntity> promotion = promotionRepository.findById(promotionId);
        Optional<AccountEntity> account = accountRepository.findById(accountId);

        if (promotion.isPresent() && account.isPresent()) {
            PromotionEntity promotionEntity = promotion.get();
            AccountEntity accountEntity = account.get();
            promotionRepository.save(promotionEntity);
        } else {
            throw new AppException(ErrorCode.ACCOUNT_NOT_EXITED);
        }
    }
}
