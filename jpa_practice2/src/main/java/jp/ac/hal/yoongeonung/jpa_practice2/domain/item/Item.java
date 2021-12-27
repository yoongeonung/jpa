package jp.ac.hal.yoongeonung.jpa_practice2.domain.item;

import jp.ac.hal.yoongeonung.jpa_practice2.domain.CategoryItem;
import jp.ac.hal.yoongeonung.jpa_practice2.domain.OrderItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ITEM")
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
public class Item {
    @Id
    @Column(name = "ITEM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private int price;
    @Column(name = "SOTCKQUANTITY")
    private int stockQuantity;
    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems = new ArrayList<>();
}
