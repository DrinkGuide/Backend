package lion6.DrinkGuide.api.payments.dto.response;

public record EasyPayDto(
        String provider,
        int amount,
        int discountAmount
) {}
