package tacos.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Marks this class as a JPA entity
@Data   // Lombok: generates getters, setters
@NoArgsConstructor // Lombok: generates no-args constructor
@AllArgsConstructor // Lombok: generates all-args constructor
public class Ingredient {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generates id
    private int id;
    private String ingredientName;   // Name of the ingredient
    private String ingredientType;  // Type like wrap, protein, etc.

}
