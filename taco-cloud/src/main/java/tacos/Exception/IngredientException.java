package tacos.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// handle custom exception for ingredient
@ResponseStatus(HttpStatus.NOT_FOUND)
public class IngredientException extends RuntimeException{
    public IngredientException(String mess){
        super(mess);
    }
}
