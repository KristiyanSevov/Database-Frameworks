package productshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import productshop.entities.User;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u FROM User as u JOIN u.soldProducts as p WHERE p.buyer IS NOT NULL" +
            " ORDER BY u.lastName, u.firstName")
    Set<User> findUsersWithSoldProducts();
}
