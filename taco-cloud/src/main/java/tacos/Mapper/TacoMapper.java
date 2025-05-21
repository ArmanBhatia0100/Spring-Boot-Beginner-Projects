package tacos.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tacos.Model.Ingredient;
import tacos.Model.Taco;
import tacos.Repository.IngredientRepository;
import tacos.Dto.TacoDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TacoMapper {

    @Autowired
    private IngredientRepository ingredientRepository;

//    converts taco entity to tacoDto
    public Taco toEntity(TacoDTO dto){
        Taco taco = new Taco();
        taco.setTacoName(dto.getTacoName());
        taco.setPrice(dto.getPrice());

        List<Ingredient> ingredients = dto.getIngredientIds().stream()
                .map(name->ingredientRepository.findByIngredientName(name))
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        taco.setIngredients(ingredients);
        return taco;
    }

//    converts tacoDto to taco entity
    public TacoDTO toDTO(Taco taco){
        TacoDTO dto = new TacoDTO();
        dto.setTacoName(taco.getTacoName());
        dto.setPrice(taco.getPrice());

        List<String> ingredientNames = taco.getIngredients().stream()
                .map(Ingredient::getIngredientName)
                .collect(Collectors.toList());
        dto.setIngredientIds(ingredientNames);
        return dto;
    }

}
