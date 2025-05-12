package tacos.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tacos.Entity.User;

public interface UserRepo extends JpaRepository<User,Integer> {
}
