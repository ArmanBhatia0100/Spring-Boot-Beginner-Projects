package tacos.Mapper;

import org.springframework.stereotype.Component;
import tacos.Model.Ingredient;
import tacos.Dto.IngredientDTO;

@Component
public class IngredientMapper {

    // converts ingredient entity to ingredientDTO
    public IngredientDTO toDTO(Ingredient ingredient){
        IngredientDTO dto = new IngredientDTO();
        dto.setIngredientName(ingredient.getIngredientName());
        dto.setIngredientType(ingredient.getIngredientType());
        return dto;
    }

//  converts ingredientDTO to ingredient entity
    public Ingredient toEntity(IngredientDTO dto){
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName(dto.getIngredientName());
        ingredient.setIngredientType(dto.getIngredientType());
        return ingredient;
    }

}
