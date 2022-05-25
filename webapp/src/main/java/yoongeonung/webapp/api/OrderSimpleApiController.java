package yoongeonung.webapp.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yoongeonung.webapp.domain.Address;
import yoongeonung.webapp.domain.Order;
import yoongeonung.webapp.domain.OrderSearch;
import yoongeonung.webapp.domain.OrderStatus;
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

  /**
   * V2. 엔티티를 조회해서 DTO로 변환(fetch join 사용X)
   *
   * - 단점: 지연로딩으로 쿼리 N번 호출
   */
  @GetMapping("/api/v2/simple-orders")
  public List<SimpleOrderDto> ordersV2() {
    List<Order> orders = orderRepository.findAll();
    List<SimpleOrderDto> result = orders.stream().map(o -> new SimpleOrderDto(o))
        .collect(Collectors.toList());
    return result;
  }

  @GetMapping("/api/v3/simple-orders")
  public List<SimpleOrderDto> ordersV3() {
    List<Order> all = orderRepository.findAllWithMemberDelivery();
    return all.stream().map(o -> new SimpleOrderDto(o)).collect(Collectors.toList());
  }

  @Data
  private static class SimpleOrderDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderDto(Order order) {
      orderId = order.getId();
      name = order.getMember().getName();
      orderDate = order.getOrderDate();
      orderStatus = order.getStatus();
      address = order.getDelivery().getAddress();
    }
  }
}
