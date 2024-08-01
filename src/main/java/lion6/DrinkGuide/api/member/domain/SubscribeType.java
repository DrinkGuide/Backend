package lion6.DrinkGuide.api.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum SubscribeType {
    NONE("NONE"),
    TRIAL("TRIAL"),
    DRINK("DRINK"),
    DRINK_SNACK("DRINK_SNACK");

    private final String key;

}