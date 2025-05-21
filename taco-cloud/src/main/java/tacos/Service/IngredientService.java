package tacos.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tacos.Exception.IngredientException;
import tacos.Model.Ingredient;
import tacos.Repository.IngredientRepository;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    // creates a new ingredient
    public Ingredient createIngredient(Ingredient ingredient) throws IngredientException {
        if(ingredient.getIngredientName().isEmpty()||
        ingredient.getIngredientType().isEmpty()){
            throw new IngredientException("Ingredient details cannot be empty.");
        }
        return ingredientRepository.save(ingredient);
    }

    // get all ingredients
    public List<Ingredient> getAllIngredients(){
        return ingredientRepository.findAll();
    }

    // get ingredient by id
    public Ingredient getIngredientsById(int id) throws IngredientException {
        return ingredientRepository.findById(id).orElseThrow(()->new IngredientException("Cannot find ingredients with this id: "+id));
    }

    // get ingredient by name
    public List<Ingredient> getIngredientsByName(String name){
        return ingredientRepository.findByIngredientName(name);
    }

    // get ingredient by type
    public List<Ingredient> getIngredientsByType(String type){
        return ingredientRepository.findByIngredientType(type);
    }

    // update ingredient by id
    public Ingredient updateIngredient(int id, Ingredient updateIngredient) throws IngredientException {
        if(!ingredientRepository.existsById(id)){
            throw new IngredientException("Cannot find ingredient with this id: "+id);
        }
        Ingredient existing = ingredientRepository.findById(id).get();
                    existing.setIngredientType(updateIngredient.getIngredientType());
                    existing.setIngredientName(updateIngredient.getIngredientName());
        return ingredientRepository.save(existing);
    }

    // delete ingredient by id
    public void deleteIngredient(int id) throws IngredientException {
        if(!ingredientRepository.existsById(id)){
            throw new IngredientException("Cannot find the ingredient with this id: "+id);
        }
        ingredientRepository.deleteById(id);
    }

}
