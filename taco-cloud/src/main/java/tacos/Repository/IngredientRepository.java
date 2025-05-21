package tacos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tacos.Model.Ingredient;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Integer> {

    // finds list of ingredients by ingredient name
    List<Ingredient> findByIngredientName(String name);

    // finds list of ingredients by ingredient type
    List<Ingredient> findByIngredientType(String name);
}
