package jp.ac.hal.yoongeonung.jpql;

import javax.persistence.*;
import java.util.List;

public class Jpqlmain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpql");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            for (int i = 0; i < 100; i++) {
                Member member = new Member();
                member.setUsername("member" + i);
                member.setAge(20 + i);
                em.persist(member);
            }
            em.flush();
            em.clear();


            TypedQuery<Member> query = em.createQuery("select m from Member m order by m.age desc ", Member.class)
                    .setFirstResult(0).setMaxResults(10);
            List<Member> members = query.getResultList();
            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
                System.out.println("m.getAge() = " + m.getAge());
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
