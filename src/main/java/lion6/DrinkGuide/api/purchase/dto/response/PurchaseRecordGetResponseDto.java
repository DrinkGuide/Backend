package lion6.DrinkGuide.api.purchase.dto.response;

import lion6.DrinkGuide.api.purchase.domain.PurchaseRecord;
import lion6.DrinkGuide.common.util.TimeUtil;

public record PurchaseRecordGetResponseDto(
        String productName,
        String productType,
        String purchaseDate
) {
    public static PurchaseRecordGetResponseDto of(PurchaseRecord purchaseRecord) {
        return new PurchaseRecordGetResponseDto(
                purchaseRecord.getProductName(),
                purchaseRecord.getProductType().getKey(),
                TimeUtil.refineDate(purchaseRecord.getCreatedDate())
        );
    }
}
