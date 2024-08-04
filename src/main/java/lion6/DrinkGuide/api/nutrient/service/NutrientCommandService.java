package lion6.DrinkGuide.api.nutrient.service;

import lion6.DrinkGuide.api.nutrient.domain.Product;
import lion6.DrinkGuide.api.nutrient.repository.NutrientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NutrientCommandService {
    private final NutrientRepository nutrientRepository;

    public void saveNutrient(Product product) {
        nutrientRepository.save(product);
    }
}
