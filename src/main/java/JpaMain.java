import inheritancemapping.practice4.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hello");
        EntityManager manager = factory.createEntityManager();
        // transaction
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        try {
            // MUSIC
            Category musicCategory = new Category();
            musicCategory.setName("MUSIC");
            // MUSIC_CHILDREN
            ArrayList<Category> musicCategories = new ArrayList<>();
            // POP
            Category pop = new Category();
            pop.setParent(musicCategory);
            pop.setName("POP");
            musicCategories.add(pop);
            // POP_CHILDREN
            ArrayList<Category> popCategories = new ArrayList<>();
            // KPOP
            Category kpop = new Category();
            kpop.setName("KPOP");
            kpop.setParent(pop);
            popCategories.add(kpop);
            // JPOP
            Category jpop = new Category();
            jpop.setName("JPOP");
            jpop.setParent(pop);
            popCategories.add(jpop);

            // POP <- POP_CHILDREN
            pop.setChild(popCategories);
            // MUSIC <- POP
            musicCategory.setChild(musicCategories);


            manager.persist(musicCategory);
            manager.persist(pop);
            manager.persist(kpop);
            manager.persist(jpop);
            //commit
            System.out.println("<------------------");
            transaction.commit();
            System.out.println("------------------>");
        }catch (Exception e) {
            transaction.rollback();
        }finally {
            // close
            manager.close();
        }
        factory.close();
    }
}
