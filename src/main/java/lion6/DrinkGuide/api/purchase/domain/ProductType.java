package lion6.DrinkGuide.api.purchase.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductType {
    DRINK("DRINK"),
    SNACK("SNACK");

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private final String key;
}
