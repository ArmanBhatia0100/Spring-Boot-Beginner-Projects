package tacos.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// handle custom exception for taco
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TacoNotFoundException extends RuntimeException{

    public TacoNotFoundException(String mess){
        super(mess);
    }
}
