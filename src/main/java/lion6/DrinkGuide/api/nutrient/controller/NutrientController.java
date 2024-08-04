package lion6.DrinkGuide.api.nutrient.controller;

import lion6.DrinkGuide.api.nutrient.domain.Product;
import lion6.DrinkGuide.api.nutrient.service.NutrientCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/nutrients")
@RequiredArgsConstructor
public class NutrientController {
    private final NutrientCommandService nutrientCommandService;
    @RequestMapping("/new")
    public String showNutrientForm(Model model) {
        return "nutrient-form"; // Thymeleaf 템플릿 파일 이름
    }

    @PostMapping
    public String submitNutrientForm(
            @RequestParam String productName,
            @RequestParam String nutrientInfo) {
        // Product 객체 생성
        Product product = Product.builder()
                .productName(productName)
                .nutrientInfo(nutrientInfo)
                .build();

        // Product 저장
        nutrientCommandService.saveNutrient(product);
        return "redirect:/api/v1/nutrients/new"; // 성공 후 리디렉션할 URL
    }

}
