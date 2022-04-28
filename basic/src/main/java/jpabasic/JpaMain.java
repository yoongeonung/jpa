package jpabasic;

import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jpabasic.valuetype.Address;
import jpabasic.valuetype.Period;

public class JpaMain {

  public static void main(String[] args) {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("basic");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction transaction = manager.getTransaction();
    transaction.begin();

    try {

      Address workAddress = new Address("seoul", "garosu-gil", "111");
      Address homeAddress = new Address("seoul", "gangnam-gil", "222");
      Member member = new Member("userA", new Period(LocalDateTime.now(), LocalDateTime.now()),
          homeAddress, workAddress);

      member.changeWorkAddress(
          new Address("pangyo", workAddress.getStreet(),
              workAddress.getZipcode()));

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
