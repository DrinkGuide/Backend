package lion6.DrinkGuide.api.purchase.dto.response;

import lion6.DrinkGuide.api.purchase.domain.ProductType;
import lion6.DrinkGuide.api.purchase.domain.PurchaseRecord;
import lion6.DrinkGuide.common.util.TimeUtil;

public record PurchaseRecordCountResponseDto(
        String productType,
        Long count
) {
    public static PurchaseRecordCountResponseDto of(String productType, Long count) {
        return new PurchaseRecordCountResponseDto(
                productType,
                count
        );
    }
}
