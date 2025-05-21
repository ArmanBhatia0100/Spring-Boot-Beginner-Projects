package tacos.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// handle custom exception for order
@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderException extends RuntimeException{
    public OrderException(String mess){
        super(mess);
    }
}
