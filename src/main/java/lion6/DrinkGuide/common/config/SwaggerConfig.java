package lion6.DrinkGuide.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("DrinkGuide API Specification")
                .description("API specification for the \"DrinkGuide\" Service.\n\n" +
                        "Health Check - [https://www.drinkguide.store/api/v1/health](https://www.drinkguide.store/api/v1/health)")
                .version("1.0.0");
    }
}
