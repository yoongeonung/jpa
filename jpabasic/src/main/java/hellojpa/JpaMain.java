package hellojpa;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        // application 로딩시점에 딱 하나만 만들어져야 한다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // transaction 단위의 행위는 entity manager를 생성해서 사용해야 한다.
        EntityManager em = emf.createEntityManager();
        /**
         * JPA에서 데이터를 변경하는 모든 작업은 트랜잭션 안에서 진행되어야 한다.
         */
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
//            Member member = new Member(1L, "JPA1");
//            em.persist(member);

            Member member = em.find(Member.class, 1L);
            member.setName("UpdatedJPA");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 엔티티매니저는 트랜잭션을 물고 작동하기 때문에 작업이 끝나면 종료하는게 중요!
        }
        emf.close();
    }
}
