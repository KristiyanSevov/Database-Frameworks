import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Ex4_EmployeesSalaryOver50000 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.createQuery("SELECT emp FROM Employee as emp WHERE emp.salary > 50000", Employee.class)
                .getResultList()
                .forEach(x -> System.out.println(x.getFirstName()));
        em.close();
        emf.close();
    }
}
