package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ITEM")
public class Item {
    @Id @Column(name = "ITEM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private int price;
    @Column(name = "STOCKQUANTITY")
    private int stockQuantity;
    @OneToMany(mappedBy = "item")
    private List<ItemCategory> itemCategories = new ArrayList<>();
}
