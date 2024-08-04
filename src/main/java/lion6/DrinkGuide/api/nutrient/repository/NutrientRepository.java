package lion6.DrinkGuide.api.nutrient.repository;

import lion6.DrinkGuide.api.nutrient.domain.Product;
import lion6.DrinkGuide.common.response.ErrorStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.webjars.NotFoundException;

import java.util.Optional;

public interface NutrientRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByProductName(String productName);

    default Product findProductByProductNameOrThrow(String productName) {
        return findProductByProductName(productName)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_PRODUCT.getMessage()));
    }

}