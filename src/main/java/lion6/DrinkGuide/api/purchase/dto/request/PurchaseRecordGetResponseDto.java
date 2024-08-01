package lion6.DrinkGuide.api.purchase.dto.request;

import lion6.DrinkGuide.api.purchase.domain.PurchaseRecord;
import lion6.DrinkGuide.common.util.TimeUtil;

public record PurchaseRecordGetResponseDto(
        String productName,
        String purchaseDate
) {
    public static PurchaseRecordGetResponseDto of(PurchaseRecord purchaseRecord) {
        return new PurchaseRecordGetResponseDto(
                purchaseRecord.getProductName(),
                TimeUtil.refineDate(purchaseRecord.getCreatedDate())
        );
    }
}
