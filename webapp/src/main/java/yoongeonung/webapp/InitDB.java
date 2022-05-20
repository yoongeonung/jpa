package yoongeonung.webapp;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yoongeonung.webapp.domain.Address;
import yoongeonung.webapp.domain.Delivery;
import yoongeonung.webapp.domain.DeliveryStatus;
import yoongeonung.webapp.domain.Member;
import yoongeonung.webapp.domain.Order;
import yoongeonung.webapp.domain.OrderItem;
import yoongeonung.webapp.domain.item.Book;


@Component
@RequiredArgsConstructor
public class InitDB {

  private final InitService initService;

  @PostConstruct
  public void init() {
    initService.dbInit1();
    initService.dbInit2();
  }


  @Component
  @Transactional
  @RequiredArgsConstructor
  static class InitService {

    private final EntityManager em;

    public void dbInit1() {
      Member member = Member.create("userA", new Address("seoul", "dsad", "1212"));
      em.persist(member);

      Book book1 = Book.create("JPA1", 1000, 100);
      em.persist(book1);

      Book book2 = Book.create("JPA2", 2000, 200);
      em.persist(book2);

      OrderItem orderItem1 = OrderItem.create(book1, 2000, 2);
      OrderItem orderItem2 = OrderItem.create(book2, 2000, 3);
      Order order = Order.create(member, Delivery.create(member.getAddress(), DeliveryStatus.READY),
          orderItem1, orderItem2);
      em.persist(order);
    }

    public void dbInit2() {
      Member member = Member.create("userB", new Address("busan", "asdd", "1111"));
      em.persist(member);

      Book book1 = Book.create("Spring1", 2000, 200);
      em.persist(book1);

      Book book2 = Book.create("Spring2", 3000, 100);
      em.persist(book2);

      OrderItem orderItem1 = OrderItem.create(book1, 10000, 5);
      OrderItem orderItem2 = OrderItem.create(book2, 6000, 2);
      Order order = Order.create(member, Delivery.create(member.getAddress(), DeliveryStatus.READY),
          orderItem1, orderItem2);
      em.persist(order);

    }
  }

}
