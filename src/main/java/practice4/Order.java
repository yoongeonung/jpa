package practice4;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter @Setter
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID") // FK
    private Member member;
    @OneToOne
    @JoinColumn(name = "DELIVERY_ID") // FK
    private Delivery delivery;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();
    @Column(name = "ORDERDATE")
    private LocalDateTime orderDate;
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
