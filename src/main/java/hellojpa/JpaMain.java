package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hello");
        EntityManager manager = factory.createEntityManager();
        // transaction
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        try {
            manager.persist(new Member("Kakao"));
            //commit
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
        }finally {
            // close
            manager.close();
        }
        factory.close();
    }
}
