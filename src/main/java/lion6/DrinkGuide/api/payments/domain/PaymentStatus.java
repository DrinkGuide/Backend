package lion6.DrinkGuide.api.payments.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus {
    PENDING("PENDING"), FAILED("FAILED"), APPROVED("APPROVED");
    private final String key;
}