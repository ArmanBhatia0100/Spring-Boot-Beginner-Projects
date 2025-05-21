package tacos.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data  // generates getters, setters
public class OrderDTO {

    // Name of the customer (should not be blank)
    @NotBlank(message = "Customer name must not be blank")
    private String username;

    // Address of the customer (should not be blank)
    @NotBlank(message = "Customer address must not be blank")
    private String address;

    // Phone number of the customer (should not be blank)
    @NotNull(message = "Customer phone number is required")
    private Long phone;

//    Order quantity must be greater or equal to 1
    @NotNull(message = "Order must contain at least one taco")
    @Size(min = 1, message = "Order must contain at least one taco")
    private List<@NotBlank(message = "Taco name cannot be blank") String> tacoNames;
}
