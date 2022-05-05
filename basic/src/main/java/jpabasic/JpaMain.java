package jpabasic;

import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import jpabasic.domain.Member;
import jpabasic.domain.Team;

public class JpaMain {

  public static void main(String[] args) {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("basic");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction transaction = manager.getTransaction();
    transaction.begin();

    try {
//      Team teamA = new Team("팀A");
//      Team teamB = new Team("팀B");
//      Team teamC = new Team("팀C");
//      manager.persist(teamA);
//      manager.persist(teamB);
//      manager.persist(teamC);
//
//      Member member1 = new Member("회원1", (byte) 1);
//      member1.changeTeam(teamA);
//      Member member2 = new Member("회원2", (byte) 2);
//      member2.changeTeam(teamA);
//      Member member3 = new Member("회원3", (byte) 3);
//      member3.changeTeam(teamB);
//      Member member4 = new Member("회원4", (byte) 4);
//      manager.persist(member1);
//      manager.persist(member2);
//      manager.persist(member3);
//      manager.persist(member4);

      Query query = manager.createQuery("select m, t from Member m join m.team t");
      List resultList = query.getResultList();
      for (Object o : resultList) {
        Object[] o1 = (Object[]) o;
        for (Object o2 : o1) {
          if (o2 instanceof Member) {
            Member member = (Member) o2;
            System.out.println("member.getUsername() = " + member.getUsername());
            System.out.println("member.getTeam().getName() = " + member.getTeam().getName());
          }
//          } else {
//            Team team = (Team) o2;
//            System.out.println("team.getName() = " + team.getName());
//          }
        }

//      TypedQuery<Member> query = manager.createQuery("select m from Member m join fetch m.team",
//          Member.class);
//      List<Member> resultList = query.getResultList();
//      for (Member member : resultList) {
//        System.out.println("member = " + member.getUsername());
//        System.out.println("member.getTeam().getName() = " + member.getTeam().getName());
//      }

        manager.flush();
        manager.clear();


      }
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
//