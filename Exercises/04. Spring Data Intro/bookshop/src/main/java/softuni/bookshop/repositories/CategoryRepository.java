package softuni.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.bookshop.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
