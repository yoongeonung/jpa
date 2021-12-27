package jp.ac.hal.yoongeonung.jpa_practice2.domain;

import jp.ac.hal.yoongeonung.jpa_practice2.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY_ITEM")
@Getter
@Setter
public class CategoryItem {
    @Id
    @Column(name = "CATEGORY_ITEM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;
}
