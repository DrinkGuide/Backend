package lion6.DrinkGuide.api.purchase.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductType {
    DRINK("DRINK"),
    SNACK("SNACK");

    private final String key;
    public String getKey() {
        return key;
    }

}
