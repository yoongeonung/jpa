import proxy.Member;
import proxy.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hello");
        EntityManager em = factory.createEntityManager();
        // transaction
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            Team team = new Team();
            team.setName("Kakao");

            Member member1 = new Member();
            member1.setUsername("Yoon");
            member1.setTeam(team);
            team.setMember(member1); // 객체관점, DB반영 X

            Member member2 = new Member();
            member2.setUsername("Kim");
            member2.setTeam(team);
            team.setMember(member2); // 객체관점, DB반영 X

            em.persist(member1);
            em.persist(member2);
            em.persist(team);
            // persistence context clear
            em.flush();
            em.clear();
            // getRef
            Member refMember1 = em.find(Member.class, member1.getId());
            Member refMember2 = em.getReference(Member.class, member2.getId());
            System.out.println("ref instanceof Member  :  " + (refMember2 instanceof Member));
            System.out.println("refMember1.getClass() = " + refMember1.getClass());
            System.out.println("refMember2.getClass() = " + refMember2.getClass());

            //commit
            System.out.println("<----------commit--------");
            transaction.commit();
            System.out.println("----------commit-------->");
        }catch (Exception e) {
            transaction.rollback();
        }finally {
            // close
            em.close();
        }
        factory.close();
    }
}
