package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hello");
        EntityManager manager = factory.createEntityManager();
        // transaction
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        try {
            // 비영속 상태
            Team team = new Team();
            team.setName("Kakao");
            manager.persist(team);
            Member member = new Member("civilian");
            // 연관관계 편의 메서드
            member.addTeam(team);
            manager.persist(member);

            // 영속화 컨텍스트 clear
            manager.flush();
            manager.clear();

            // DB에서 조회
            Member findMember = manager.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();
            for (Member member1 : members) {
                System.out.println("member1 = " + member1);
            }


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
