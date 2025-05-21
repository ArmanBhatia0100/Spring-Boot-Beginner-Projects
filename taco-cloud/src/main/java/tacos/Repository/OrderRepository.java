package tacos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tacos.Model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    // finds list of order by username
    public List<Order> findByUsername(String user);

}
