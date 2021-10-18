package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hello");
        EntityManager manager = factory.createEntityManager();
        // transaction
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        try {
            // 비영속 상태
            Member kakao = new Member("Kakao");
            Member naver = new Member("Naver");
            // 영속화
            manager.persist(kakao);
            manager.persist(naver);
            // 엔티티 삭제
            System.out.println("<------------------");
            manager.remove(kakao);
            System.out.println("------------------>");
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
