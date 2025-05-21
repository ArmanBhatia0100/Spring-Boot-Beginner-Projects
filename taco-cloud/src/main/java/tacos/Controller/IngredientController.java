package tacos.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tacos.Dto.IngredientDTO;
import tacos.Mapper.IngredientMapper;
import tacos.Model.Ingredient;
import tacos.Service.IngredientService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private IngredientMapper mapper;

//    create a new ingredient
    @PostMapping
    public ResponseEntity<?> createIngredient(@Valid @RequestBody IngredientDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
        }

        Ingredient saved = ingredientService.createIngredient(mapper.toEntity(dto));
        return ResponseEntity.ok(mapper.toDTO(saved));
    }

//    get all ingredient
    @GetMapping
    public ResponseEntity<?> getAllIngredients() {
        List<IngredientDTO> dtos = ingredientService.getAllIngredients()
                .stream().map(mapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

//    get ingredient by id
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getIngredientsById(@PathVariable int id) {
        return ResponseEntity.ok(mapper.toDTO(ingredientService.getIngredientsById(id)));
    }

//  get ingredient by name
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getIngredientsByName(@PathVariable String name) {
        List<IngredientDTO> dtos = ingredientService.getIngredientsByName(name)
                .stream().map(mapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

//    get ingredient by type
    @GetMapping("/type/{type}")
    public ResponseEntity<?> getIngredientsByType(@PathVariable String type) {
        List<IngredientDTO> dtos = ingredientService.getIngredientsByType(type)
                .stream().map(mapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

//    update ingredient by id
    @PutMapping("/{id}")
    public ResponseEntity<?> updateIngredient(@PathVariable int id, @Valid @RequestBody IngredientDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
        }

        Ingredient updated = ingredientService.updateIngredient(id, mapper.toEntity(dto));
        return ResponseEntity.ok(mapper.toDTO(updated));
    }

//    delete ingredient by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable int id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.ok("Ingredient deleted successfully");
    }
}
