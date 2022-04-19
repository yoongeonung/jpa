package jpabasic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("basic");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        try {

            Member member = new Member();
            member.setId(1L);
            member.setName("basicMember");

            Locker locker = new Locker();
            locker.setId(1L);
            locker.setName("basicMember's locker");

            member.setLocker(locker);

            manager.persist(locker);
            manager.persist(member);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            manager.close();
        }
        factory.close();
    }
}
