package prac5;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY) // default EAGER -> LAZY로 변경
    @JoinColumn(name = "ORDER_ID") // FK
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID") // FK
    private Item item;
    @Column(name = "ORDERPRICE")
    private int orderPrice;
    @Column(name = "COUNT")
    private int count;
}
