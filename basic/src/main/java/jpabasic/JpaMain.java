package jpabasic;

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

//      Team team = new Team("OneTeam");
//      manager.persist(team);
//
//      for (int i = 0; i < 50; i++) {
//        Member member = new Member("member" + i, (byte) i);
//        member.changeTeam(team);
//        manager.persist(member);
//      }
//
//      manager.flush();
//      manager.clear();

      Query query = manager.createQuery(
          "select m, t from Member m left join Team t on m.id = t.id");
      query.getResultList();

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