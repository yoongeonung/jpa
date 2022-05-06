package jpabasic;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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

      List<Member> members = manager
          .createNamedQuery("Member.findByUsername", Member.class)
          .setParameter("username", "회원1")
          .getResultList();
      for (Member member : members) {
        System.out.println("member = " + member);
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