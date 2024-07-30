package lion6.DrinkGuide.api.payments.dto.request;

public record PaymentsApproveRequestDto (
        String orderId,
        String paymentKey,
        int amount
){}