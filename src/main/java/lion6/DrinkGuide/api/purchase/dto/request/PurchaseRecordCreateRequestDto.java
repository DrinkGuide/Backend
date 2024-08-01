package lion6.DrinkGuide.api.purchase.dto.request;

import java.time.LocalDateTime;

public record PurchaseRecordCreateRequestDto(
        String productName,
        String productType
) {
}
