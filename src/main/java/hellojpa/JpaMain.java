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
            // 변경감지
            System.out.println("<------------------");
            kakao.setName("Daum");
            System.out.println("------------------>");

            //commit - 이 시점에 쓰기지연 SQL저장소에 담겨있던 UPDATE쿼리문이 날라간다
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
