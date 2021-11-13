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
            team.addMember(member1);

            Member member2 = new Member();
            member2.setUsername("Kim");
            team.addMember(member2);

            em.persist(team); // cacade


            // persistence context clear
            em.flush();
            em.clear();
            // get member
            Member member = em.find(Member.class, member1.getId());
            // get team
            System.out.println("------------------------------");
            System.out.println("team name = " + member.getTeam().getName());
            System.out.println("------------------------------");

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