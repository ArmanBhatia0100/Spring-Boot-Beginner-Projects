package tacos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tacos.Model.Taco;

import java.util.List;

@Repository
public interface TacoRepository extends JpaRepository<Taco,Integer> {

    // finds list of tacos by taco name
    List<Taco> findByTacoName(String name);


}
