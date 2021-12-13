package jp.ac.hal.yoongeonung.jpa_practice1.domain.Item;

import jp.ac.hal.yoongeonung.jpa_practice1.domain.CategoryItem;
import jp.ac.hal.yoongeonung.jpa_practice1.exception.NotEnoughStockQuantityException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter @Setter
public class Item {
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems = new ArrayList<>();

    /*
        재고수량 증가
     */
    public void addStock(int stockQuantity) {
        this.stockQuantity += stockQuantity;
    }


    /*
        재고수량 감소
     */
    public void decreaseStock(int stockQuantity) {
        this.stockQuantity = getStockQuantity(stockQuantity);
    }

    private int getStockQuantity(int stockQuantity) {
        int restQuantity = this.stockQuantity - stockQuantity;
        if (restQuantity < 0) {
            throw new NotEnoughStockQuantityException("재고수량이 부족합니다.");
        }
        return restQuantity;
    }
}
