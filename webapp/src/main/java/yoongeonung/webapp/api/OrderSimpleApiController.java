package yoongeonung.webapp.api;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yoongeonung.webapp.domain.Order;
import yoongeonung.webapp.domain.OrderSearch;
import yoongeonung.webapp.repository.OrderRepository;


/**
 * xToOne(ManyToOne, OneToOne) 관계 최적화 * Order Order -> Member Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

  private final OrderRepository orderRepository;

  /**
   * V1. 엔티티 직접 노출 - Hibernate5Module 모듈 등록, LAZY=null 처리 - 양방향 관계 문제 발생 -> @JsonIgnore
   */
  @GetMapping("/api/v1/simple-orders")
  public List<Order> ordersV1() {
    // 빈 객체(new OrderSearch())를 던져줌으로써 예외상황 방지
    List<Order> orders = orderRepository.findAllByString(new OrderSearch());
    for (Order order : orders) {
      order.getMember().getName();
      order.getDelivery().getAddress();
      order.getOrderItems().get(0);
    }
    return orders;
  }
}
