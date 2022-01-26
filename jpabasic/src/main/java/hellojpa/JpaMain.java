package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;

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
            Member member = new Member("naver", (byte) 14, RoleType.ADMIN, LocalDate.now(),LocalDate.now(), "qjklwqjekljadmin");
            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 엔티티매니저는 트랜잭션을 물고 작동하기 때문에 작업이 끝나면 종료하는게 중요!
        }
        emf.close();
    }
}
