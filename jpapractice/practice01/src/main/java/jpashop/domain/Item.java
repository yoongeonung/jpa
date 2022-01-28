package jpashop.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "ITEM")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private int price;
    @Column(name = "STOCKQUANTITY")
    private int stockQuantity;
}
