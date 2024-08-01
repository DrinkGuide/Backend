package lion6.DrinkGuide.api.payments.dto.request;


public record PaymentsInitializeRequestDto(
        String orderId,
        int amount,
        String subscribeType
) {
}
