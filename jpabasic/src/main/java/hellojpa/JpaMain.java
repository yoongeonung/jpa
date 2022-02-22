package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * JPA에서 데이터를 변경하는 모든 작업은 트랜잭션 안에서 진행되어야 한다.
 */
public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Child child1 = new Child();
            Child child2 = new Child();
            child1.setName("kakao");
            child2.setName("line");

            Parent parent = new Parent();
            parent.setName("KOREA");
            parent.addChild(child1, child2);

            em.persist(parent);

            em.flush();
            em.clear();

            Parent parent1 = em.find(Parent.class, 1L);
            //
            parent1.getChildren().remove(0);
            //

            //
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 엔티티매니저는 트랜잭션을 물고 작동하기 때문에 작업이 끝나면 종료하는게 중요!
        }
        emf.close();
    }
}
