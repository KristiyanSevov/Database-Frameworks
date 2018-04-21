import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Ex11_RemoveTowns {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String townName = reader.readLine();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        try {
            Town town = em.createQuery("SELECT t FROM Town as t WHERE t.name =:name", Town.class)
                    .setParameter("name", townName)
                    .getSingleResult();
            List<Address> addresses = em
                    .createQuery("SELECT add FROM Address as add WHERE add.town = :town", Address.class)
                    .setParameter("town", town)
                    .getResultList();
            em.getTransaction().begin();
            em.createQuery("UPDATE Employee as emp SET emp.address = null WHERE emp.address in :addresses")
                    .setParameter("addresses", addresses)
                    .executeUpdate();
            for (Address add : addresses) {
                em.remove(add);
            }
            em.remove(town);
            em.getTransaction().commit();
            System.out.printf("%d addresses in %s deleted%n", addresses.size(), townName);
        } catch (NoResultException e) {
            System.out.println("No such town");
        }
        em.close();
        emf.close();
    }
}
