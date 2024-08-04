package lion6.DrinkGuide.api.nutrient.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String nutrientInfo;

    @Builder
    public Product(String productName, String nutrientInfo) {
        this.productName = productName;
        this.nutrientInfo = nutrientInfo;
    }
}
