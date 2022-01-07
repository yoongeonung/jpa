package yoongeonung.jpa_practice2.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yoongeonung.jpa_practice2.domain.Address;
import yoongeonung.jpa_practice2.domain.Order;
import yoongeonung.jpa_practice2.domain.OrderStatus;
import yoongeonung.jpa_practice2.repository.OrderRepository;
import yoongeonung.jpa_practice2.repository.OrderSearch;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * xToOne (ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    /**
     * Lazy loading으로 설정되엇 proxy객체가 나올경우
     * jackson라이브러리가 json으로 매핑을 할수없어 exception이 발생한다.
     * 이 경우 hibernate5module을 사용하면 lazy loading을 무시(null로 생각)하고
     * json으로 매핑을 진행할게 할수있다. 물론 옵션을 사용하면 Lazy loading을 강제 초기화도 가능하다.
     */
    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); // Lazy Loading을 강제적으로 초기화
            order.getDelivery().getStatus(); // Lazy Loading을 강제적으로 초기화
        }
        return all;
    }

    /**
     * Lazy Loading에 대한 N+1문제가 여전히 존재한다.
     * EAGER로 바꾼다고 해결되지 않음, 오히려 악화
     */
    @GetMapping("/api/v2/simple-orders")
    public Result ordersV2() {
        List<SimpleOrderDto> result = orderRepository.findAllByString(new OrderSearch())
                .stream()
                .map(SimpleOrderDto::new)
                .collect(Collectors.toList());
        return new Result(result);
    }

    @AllArgsConstructor
    @Data
    private static class Result<T> {
        private T data;
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
