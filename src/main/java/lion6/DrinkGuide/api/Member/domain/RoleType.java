package lion6.DrinkGuide.api.Member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {
    USER("USER"), ADMIN("ADMIN");
    private final String key;
}