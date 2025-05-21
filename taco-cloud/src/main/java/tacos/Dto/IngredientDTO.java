package tacos.Dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data // generates getters, setters
public class IngredientDTO {

    // Name of the ingredient (should not be blank)
    @NotBlank(message = "Ingredient name must not be blank")
    private String ingredientName;

    // Type of the ingredient (like VEGGIES, SAUCE, etc.), should not be blank
    @NotBlank(message = "Ingredient type must not be blank")
    private String ingredientType;
}
