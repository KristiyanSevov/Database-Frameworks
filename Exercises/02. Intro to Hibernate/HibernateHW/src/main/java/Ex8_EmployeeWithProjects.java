import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex8_EmployeeWithProjects {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(reader.readLine());

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        try {
            Employee employee = em
                    .createQuery("SELECT emp FROM Employee as emp WHERE emp.id = :id", Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();
            System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
            em.createQuery(
                    "SELECT p FROM Employee as emp " +
                            "JOIN emp.projects as p " +
                            "WHERE emp.id =:id " +
                            "ORDER BY p.name", Project.class)
                    .setParameter("id", id)
                    .getResultList()
                    .forEach(x -> System.out.printf("\t%s%n", x.getName()));
        } catch (NoResultException e) {
            System.out.println("No such employee");
        }
        em.close();
        emf.close();
    }
}
