package tacos.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestControllerAdvice
public class GlobalExceptionHandler {

//    it handles all order exception
    @ExceptionHandler(OrderException.class)
    public ResponseEntity<String> handleOrderException(OrderException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    //    it handles all taco exception
    @ExceptionHandler(TacoNotFoundException.class)
    public ResponseEntity<String> handleTacoNotFoundException(TacoNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    //    it handles all ingredient exception
    @ExceptionHandler(IngredientException.class)
    public ResponseEntity<String> handleIngredientException(IngredientException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


}
