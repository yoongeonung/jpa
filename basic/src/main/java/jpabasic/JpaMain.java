package jpabasic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jpabasic.mtom.Member;
import jpabasic.mtom.Product;

public class JpaMain {

  public static void main(String[] args) {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("basic");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction transaction = manager.getTransaction();
    transaction.begin();

    try {

      Member member = new Member();
      member.setUsername("testMember");

      Product product1 = new Product();
      product1.setName("testProduct1");
      Product product2 = new Product();
      product2.setName("testProduct2");
      Product product3 = new Product();
      product3.setName("testProduct3");
      member.getProducts().add(product1);
      member.getProducts().add(product2);
      member.getProducts().add(product3);

      manager.persist(product1);
      manager.persist(product2);
      manager.persist(product3);
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
//