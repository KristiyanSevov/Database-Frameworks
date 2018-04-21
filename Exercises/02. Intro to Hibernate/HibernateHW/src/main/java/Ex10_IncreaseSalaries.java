import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

//sample output misses Engineering employees - remove that to get it
public class Ex10_IncreaseSalaries {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        List<Employee> employees = em
                .createQuery("SELECT emp FROM Employee as emp JOIN emp.department as d " +
                                "WHERE d.name in ('Engineering', 'Tool Design', 'Marketing', 'Information Services')" +
                                "ORDER BY emp.id",
                        Employee.class)
                .getResultList();
        em.getTransaction().begin();
        for (Employee emp : employees) {
            emp.setSalary(emp.getSalary().multiply(new BigDecimal(1.12)));
            System.out.printf("%s %s ($%.2f)%n", emp.getFirstName(), emp.getLastName(), emp.getSalary());
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}