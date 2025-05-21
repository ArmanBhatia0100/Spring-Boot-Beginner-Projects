package tacos.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity // Marks this class as a JPA entity
@Data    // Lombok: generates getters, setters
@Table(name = "orders") // Table name will be orders
@NoArgsConstructor  // Lombok: generates no-args constructor
@AllArgsConstructor // Lombok: generates all-args constructor
public class Order {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates id
    private int id;
    private String username; // Name of the user who placed the order
    private String address; // Deliver address
    private Long total; // Total price of the order
    private Long phone; // Phone number of the user
    @ManyToMany // Many-to-many relationship with tacos
    @JoinTable(
            name = "order_tacos",   // Join table name
            joinColumns = @JoinColumn(name = "order_id"),   // Refers to current entity
            inverseJoinColumns = @JoinColumn(name = "taco_id")  // Refers to Taco entity
    )
    private List<Taco> tacos;   // List of tacos in the order
}
