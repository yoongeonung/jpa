package prac5;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@Table(name = "ORDERS") // ORDERS의 이유 : DBMS에 따라 ORDER가 예약어인 경우가 있다.
@NoArgsConstructor
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID") // FK
    private Member member;
    @OneToMany(mappedBy = "order") // 양방향 매핑
    private List<OrderItem> orderItems = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "DELIVERY_ID") // FK
    private Delivery delivery;
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus stauts;

    public Order(Member member, List<OrderItem> orderItems, Delivery delivery, LocalDateTime orderDate, OrderStatus stauts) {
        this.member = member;
        this.orderItems = orderItems;
        this.delivery = delivery;
        this.orderDate = orderDate;
        this.stauts = stauts;
    }
}
