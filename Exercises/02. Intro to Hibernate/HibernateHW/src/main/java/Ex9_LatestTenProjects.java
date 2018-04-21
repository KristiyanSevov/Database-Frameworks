import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;

public class Ex9_LatestTenProjects {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        em.createQuery("SELECT p FROM Project as p ORDER BY p.startDate DESC ", Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(x -> System.out.printf("Project name: %s%n" +
                                "\tProject Description: %s%n" +
                                "\tProject Start Date: %s%n" +
                                "\tProject End Date: %s%n",
                        x.getName(), x.getDescription(), x.getStartDate(), x.getEndDate()));
        em.close();
        emf.close();
    }
}
