import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex12_EmployeesByName {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String start = reader.readLine();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.createQuery("SELECT emp FROM Employee as emp WHERE emp.firstName LIKE CONCAT(:str, '%')",
                Employee.class)
                .setParameter("str", start)
                .getResultList()
                .forEach(x -> System.out.printf("%s %s - %s - ($%.2f)%n",
                        x.getFirstName(), x.getLastName(), x.getJobTitle(), x.getSalary()));
        em.close();
        emf.close();
    }
}
