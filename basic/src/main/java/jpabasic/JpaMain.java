package jpabasic;

import jpabasic.onetoone.Member;

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

            Member member1 = new Member();
            member1.setName("member1");
            manager.persist(member1);

            Member member2 = new Member();
            member2.setName("member2");
            manager.persist(member2);

            manager.flush();
            manager.clear();

            Member m1 = manager.getReference(Member.class, member1.getId());
            System.out.println("m1 = " + m1.getClass());
            Member m2 = manager.find(Member.class, member2.getId());
            System.out.println("m2 = " + m2.getClass());

            System.out.println("m1 == m2 : " + (m1.getClass() == m2.getClass()));
            System.out.println("m1 == Member : " + (m1 instanceof Member));

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
