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
            Locker locker = new Locker();
            locker.setName("1000엔락커");
            System.out.println("<------------------");
            manager.persist(locker);
            System.out.println("------------------>");

            Member member = new Member();
            member.setName("OneToOne");
            member.setLocker(locker); // 객체 지향 관점
            manager.persist(member);

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
