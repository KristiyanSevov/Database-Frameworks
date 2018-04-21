import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Ex13_MaximumSalaries {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        em.createQuery("SELECT d.name, max(emp.salary) FROM Employee as emp " +
                "JOIN emp.department as d " +
                "GROUP BY d.name " +
                "HAVING max(emp.salary) < 30000 OR max(emp.salary) > 70000 " +
                "ORDER BY d.id", Object[].class)
                .getResultList()
                .forEach(x -> System.out.printf("%s - %s%n", x[0], x[1]));
        em.close();
        emf.close();
    }
}
