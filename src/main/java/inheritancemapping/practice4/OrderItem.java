package inheritancemapping.practice4;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
@Getter @Setter
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;
    @Column(name = "ORDERPRICE")
    private int orderPrice;
    @Column(name = "COUNT")
    private int count;
}
