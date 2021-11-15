package prac5;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ORDER_ID") // FK
    private Order order;
    @ManyToOne
    @JoinColumn(name = "ITEM_ID") // FK
    private Item item;
    @Column(name = "ORDERPRICE")
    private int orderPrice;
    @Column(name = "COUNT")
    private int count;
}
