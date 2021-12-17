//package jp.ac.hal.yoongeonung.jpa_practice1.service;
//
//import jp.ac.hal.yoongeonung.jpa_practice1.domain.Address;
//import jp.ac.hal.yoongeonung.jpa_practice1.domain.Item.Book;
//import jp.ac.hal.yoongeonung.jpa_practice1.domain.Member;
//import jp.ac.hal.yoongeonung.jpa_practice1.domain.Order;
//import jp.ac.hal.yoongeonung.jpa_practice1.domain.OrderStatus;
//import jp.ac.hal.yoongeonung.jpa_practice1.exception.NotEnoughStockQuantityException;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class OrderServiceTest {
//
//    @Autowired
//    private EntityManager em;
//    @Autowired
//    private OrderService orderService;
//
//    @Test
//    @DisplayName("상품주문")
//    void order() {
//        //given
//        Member member = getMember("Kakao", new Address("seoul", "han-river", "110-220"));
//
//        Book book = getBook("JPA", 3000, 100);
//        //when
//        Long orderId = orderService.order(member.getId(), book.getId(), 2);
//
//        //then
//        Order findOrder = orderService.findOne(orderId);
//
//        // 주문 상태가 ORDER 여야 한다.
//        Assertions.assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
//        // 주문 상품 종류가 한개여야 한다.
//        Assertions.assertThat(findOrder.getOrderItems().size()).isEqualTo(1);
//        // 주문 가격은 상품가격 * 수량이어야 한다.
//        Assertions.assertThat(findOrder.getTotalPrice()).isEqualTo(book.getPrice() * 2);
//        // 주문후 상품수량은 주문수량만큼 재고가 줄어야 한다.
//        Assertions.assertThat(book.getStockQuantity()).isEqualTo(98);
//    }
//
//    @Test
//    @DisplayName("주문취소")
//    void cancelOrder() {
//        //given
//        Member member = getMember("Wooah", new Address("Jamsil", "Olympic", "220-220"));
//        Book book = getBook("MySQL", 2000, 10);
//
//        int orderCount = 5;
//        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
//        //when
//        orderService.cancelOrder(orderId);
//        //then
//        // 주문 상태가 CANCEL이어야 한다.
//        Order order = orderService.findOne(orderId);
//        Assertions.assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL);
//        // 재고가 원복되어야 한다.
//        Assertions.assertThat(book.getStockQuantity()).isEqualTo(10);
//
//    }
//
//    @Test
//    @DisplayName("상품주문_재고수량초과")
//    void validateStockQuantity() {
//        //given
//        Member member = getMember("Line", new Address("Pangyo", "23", "220-110"));
//        Book book = getBook("Spring", 3000, 100);
//        //when
//        int orderCount = 101;
//        //then
//        assertThrows(NotEnoughStockQuantityException.class,
//                () -> orderService.order(member.getId(), book.getId(), orderCount));
//    }
//    // Test용도
//    private Book getBook(String name, int price, int stockQuantity) {
//        Book book = new Book();
//        book.setName(name);
//        book.setPrice(price);
//        book.setStockQuantity(stockQuantity);
//        em.persist(book);
//        return book;
//    }
//
//    private Member getMember(String name, Address address) {
//        Member member = new Member();
//        member.setName(name);
//        member.setAddress(address);
//        em.persist(member);
//        return member;
//    }
//
//
//}