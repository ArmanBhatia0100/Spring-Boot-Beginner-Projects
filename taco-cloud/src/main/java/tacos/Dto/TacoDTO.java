package tacos.Dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data // generates getters, setters
public class TacoDTO {

    // Name of the Taco (should not be blank)
    @NotBlank(message = "Taco name must not be blank")
    private String tacoName;

    // Price of the Taco (must be at least 1 and not null)
    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be at least 1")
    private Long price;

    // List of ingredient IDs used in the Taco (must have at least one and none can be blank)
    @NotNull(message = "Ingredient list must not be null")
    @Size(min = 1, message = "At least one ingredient is required")
    private List<@NotBlank(message = "Ingredient ID cannot be blank") String> ingredientIds;
}
