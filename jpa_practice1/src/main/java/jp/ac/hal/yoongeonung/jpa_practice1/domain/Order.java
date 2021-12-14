package jp.ac.hal.yoongeonung.jpa_practice1.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @Setter
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Setter
    private LocalDateTime orderDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // 연관관계 편의 메서드
    public void addMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    //　생성 메서드 - 생성에 관한 로직들을 응집해둔다, 문제가 있다면 이곳만 바꾸면 된다.
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.addMember(member);
        order.setOrderDate(LocalDateTime.now());
        order.addDelivery(delivery);
        order.setStatus(OrderStatus.ORDER);
        /**
         * for (OrderItem orderItem : orderItems) {
         *             order.addOrderItem(orderItem);
         *         }
         */
        Arrays.stream(orderItems).forEach(order::addOrderItem);
        return order;
    }

//    protected Order() {
//    }

    // 비즈니스 로직

    /**
     * 주문 취소
     */
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송 완료된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        /**
         * for (OrderItem orderItem : orderItems) {
         *             orderItem.cancel();
         *         }
         */
        orderItems.forEach(OrderItem::cancel);
    }

    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice() {
        /**
         * for (OrderItem orderItem : orderItems) {
         *             totalPrice += orderItem.getTotalPrice();
         *         }
         */
        int totalPrice = orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
        return totalPrice;
    }

}
