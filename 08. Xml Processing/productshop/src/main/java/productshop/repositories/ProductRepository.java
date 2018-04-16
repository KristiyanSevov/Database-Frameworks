package productshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import productshop.entities.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT p FROM Product p " +
            "WHERE p.buyer IS NULL AND p.price >= :from AND p.price <= :to ORDER BY p.price ASC")
    List<Product> findProductsInRange(@Param(value = "from") BigDecimal from, @Param(value = "to") BigDecimal to);
}
