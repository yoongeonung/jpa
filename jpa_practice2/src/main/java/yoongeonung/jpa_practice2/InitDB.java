package yoongeonung.jpa_practice2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yoongeonung.jpa_practice2.domain.*;
import yoongeonung.jpa_practice2.domain.item.Book;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

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
            Member member = createMember("userA", "Seoul", "Garosu-gil", "111-1111");
            em.persist(member);

            Book book1 = createBook("JPA1 BOOK", "Yoon", "1q2w3e4r", 10000, 100);
            em.persist(book1);

            Book book2 = createBook("JPA2 BOOK", "Kim", "5t6y7u8i", 20000, 200);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 2);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 3);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }


        public void dbInit2() {
            Member member = createMember("userB", "Busan", "Haeundae-gil", "222-2222");
            em.persist(member);

            Book book1 = createBook("SPRING1 BOOK", "Park", "qawsedrf", 20000, 200);
            em.persist(book1);

            Book book2 = createBook("SPRING2 BOOK", "Moon", "azsxdcfv", 40000, 300);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 4);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private Book createBook(String bookName, String author, String isbn, int price, int stockQuantity) {
            Book book1 = new Book();
            book1.setName(bookName);
            book1.setAuthor(author);
            book1.setIsbn(isbn);
            book1.setPrice(price);
            book1.setStockQuantity(stockQuantity);
            return book1;
        }

        private Member createMember(String username, String city, String street, String postcode) {
            Member member = new Member();
            member.setName(username);
            member.setAddress(new Address(city, street, postcode));
            return member;
        }

    }
}
