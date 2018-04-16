package productshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import productshop.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
