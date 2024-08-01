package lion6.DrinkGuide.api.purchase.dto.response;

import lion6.DrinkGuide.api.purchase.domain.ProductType;

public record PurchaseRecordCountResponseDto(
        String productType,
        Long count
) {
    public static PurchaseRecordCountResponseDto of(ProductType productType, Long count) {
        return new PurchaseRecordCountResponseDto(
                productType.getKey(),
                count
        );
    }
}
