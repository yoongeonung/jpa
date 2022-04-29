package jpabasic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jpabasic.valuetype.Address;

public class JpaMain {

  public static void main(String[] args) {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("basic");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction transaction = manager.getTransaction();
    transaction.begin();

    try {
      Member member1 = new Member();
      member1.getAddresseHistory().add(new Address("seoul", "garosu-gil", "1111"));
      member1.getAddresseHistory().add(new Address("busan", "haeundae-gil", "2222"));

      member1.getFavoriteFoods().add("kimchi-jjigae");
      member1.getFavoriteFoods().add("kong-guksu");
      manager.persist(member1);

      manager.flush();
      manager.clear();

      Member findMember = manager.find(Member.class, 1L);
      findMember.getAddresseHistory().remove(new Address("busan", "haeundae-gil", "2222"));
      findMember.getAddresseHistory().add(new Address("ulsan", "ulsan-gil", "3333"));

      findMember.getFavoriteFoods().remove("kong-guksu");
      findMember.getFavoriteFoods().add("janchi-guksu");


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
