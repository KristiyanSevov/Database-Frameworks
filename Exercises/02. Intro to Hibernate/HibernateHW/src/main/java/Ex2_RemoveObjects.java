import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Ex2_RemoveObjects {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Town> towns = em.createQuery("SELECT t FROM Town as t", Town.class).getResultList();
        for (Town t : towns) {
            if (t.getName().length() > 5) {
                em.detach(t);
            }
        }
        for (Town t : towns) {
            if (em.contains(t)) {
                t.setName(t.getName().toLowerCase());
                em.persist(t);
            }
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
