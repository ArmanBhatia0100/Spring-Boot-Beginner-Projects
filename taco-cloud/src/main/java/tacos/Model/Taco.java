package tacos.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity // Marks this class as a JPA entity
@Data   // Lombok: generates getters, setters
@NoArgsConstructor  // Lombok: generates no-args constructor
@AllArgsConstructor // Lombok: generates all-args constructor
public class Taco {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates id
    private int id;
    private Long price; // Price of the taco
    private String tacoName; // Name of the taco
    @ManyToMany // Many-to-many relationship with ingredients
    @JoinTable(
            name = "taco_ingredients",  // Join table name
            joinColumns = @JoinColumn(name = "taco_id"),   // Refers to current entity
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")    // Refers to ingredients entity
    )
    private List<Ingredient> ingredients;
}
