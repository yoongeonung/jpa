package jp.ac.hal.yoongeonung.jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Jpqlmain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpql");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setAge(20);
            member.setUsername("Kakao");

            em.persist(member);
            String name = "Kakao";
            List<Member> members = em.createQuery("select m from Member m where m.username = :name", Member.class).setParameter("name", name).getResultList();
            for (Member member1 : members) {
                System.out.println("member1 = " + member1.getUsername());
            }

            // commit
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
