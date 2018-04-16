package cardealer.repositories;

import cardealer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    @Query(value = "SELECT c FROM Customer as c WHERE SIZE(c.sales) > 0")
    List<Customer> findCustomersWithSales();
}
