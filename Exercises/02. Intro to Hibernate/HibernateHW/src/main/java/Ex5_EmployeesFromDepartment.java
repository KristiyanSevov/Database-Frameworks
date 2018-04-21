import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Ex5_EmployeesFromDepartment {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        List<Object[]> employees = em
                .createQuery("SELECT emp.firstName, emp.lastName, emp.salary, dept.name " +
                        "FROM Employee as emp JOIN emp.department as dept " +
                        "WHERE dept.name = 'Research and Development' " +
                        "ORDER BY emp.salary, emp.id", Object[].class)
                .getResultList();
        employees.forEach(x -> System.out.printf("%s %s from %s - $%s%n", x[0], x[1], x[3], x[2]));
        em.close();
        emf.close();
    }
}
