package jp.ac.hal.yoongeonung.jpa_practice1.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@DiscriminatorColumn
@Inheritance
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems = new ArrayList<>();
}
