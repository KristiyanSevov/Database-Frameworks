import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//some additional ordering is used in the sample output but not specified
public class Ex7_AddressesWithCount {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        em.createQuery("SELECT a.text, t.name, count(emp) as emp_count FROM Employee as emp " +
                "JOIN emp.address as a " +
                "JOIN a.town as t " +
                "GROUP BY a.text, t.name " +
                "ORDER BY emp_count DESC, t.id, a.id ", Object[].class)
                .setMaxResults(10)
                .getResultList()
                .forEach(x -> System.out.printf("%s, %s - %s employees%n", x[0], x[1], x[2]));
        em.close();
        emf.close();
    }
}