import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Ex6_NewAddressAndUpdating {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String lastName = reader.readLine();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Address address = new Address();
        address.setText("Vitoshka 15");
        em.persist(address);
        TypedQuery<Employee> query = em.
                createQuery("SELECT emp FROM Employee as emp WHERE emp.lastName =:last", Employee.class);
        query.setParameter("last", lastName);
        List<Employee> employees = query.getResultList();
        if (employees.size() != 0) {
            employees.get(0).setAddress(address);
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
