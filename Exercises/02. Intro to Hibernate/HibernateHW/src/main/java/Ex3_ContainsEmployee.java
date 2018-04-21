import com.sun.org.apache.bcel.internal.generic.LXOR;
import com.sun.org.apache.bcel.internal.generic.Type;
import entities.Employee;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Ex3_ContainsEmployee {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] names = reader.readLine().split(" ");
        String firstName = names[0];
        String lastName = names[1];

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        List<Employee> employees = em
                .createQuery("SELECT emp FROM Employee as emp " +
                        "WHERE emp.firstName =:first AND emp.lastName =:last", Employee.class)
                .setParameter("first", firstName)
                .setParameter("last", lastName)
                .getResultList();
        System.out.println(employees.size() != 0 ? "Yes" : "No");
        em.close();
        emf.close();
    }
}
