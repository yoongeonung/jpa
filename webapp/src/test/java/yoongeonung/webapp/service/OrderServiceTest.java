package yoongeonung.webapp.service;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import yoongeonung.webapp.domain.Address;
import yoongeonung.webapp.domain.Member;
import yoongeonung.webapp.domain.Order;
import yoongeonung.webapp.domain.OrderStatus;
import yoongeonung.webapp.domain.item.Book;
import yoongeonung.webapp.domain.item.Item;
import yoongeonung.webapp.repository.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

  @PersistenceContext
  EntityManager em;

  @Autowired
  OrderService orderService;
  @Autowired
  OrderRepository orderRepository;

  @Test
  public void order() {
//Given
    Member member = createMember();
    Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고
    int orderCount = 2;
//When
    Long orderId = orderService.order(member.getId(), item.getId(),
        orderCount);
//Then
    Order getOrder = orderRepository.findOne(orderId); assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER,
        getOrder.getStatus());
    assertEquals("주문한 상품 종류 수가 정확해야 한다.",1,
        getOrder.getOrderItems().size());
    assertEquals("주문 가격은 가격 * 수량이다.", 10000 * 2,
        getOrder.getTotalPrice());
    assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, (int)item.getStockQuantity());

  }


  private Member createMember() {
    Member member = Member.create("회원1", new Address("서울", "강가", "123-123"));
    em.persist(member);
    return member;

  }

  private Book createBook(String name, int price, int stockQuantity) {

    Book book = new Book();
    book.setName(name);
    book.setStockQuantity(stockQuantity);
    book.setPrice(price);
    em.persist(book);

    return book;
  }
}