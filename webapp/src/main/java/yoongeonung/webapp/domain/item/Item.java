package yoongeonung.webapp.domain.item;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import yoongeonung.webapp.common.exceptions.NotEnoughStcokException;
import yoongeonung.webapp.domain.CategoryItem;

@Slf4j
@Entity
@Inheritance
@DiscriminatorColumn
@Getter
@Setter
public abstract class Item {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "item_id")
  private Long id;

  @Column(length = 50)
  private String name;

  private Integer price;
  private Integer stockQuantity;

  @OneToMany(mappedBy = "item")
  private List<CategoryItem> categoryItems = new ArrayList<>();

  public void increaseStock(int quantity) {
    log.info("quantity = {} ", quantity);
    stockQuantity += quantity;
  }

  public void decreaseStock(int quantity) {
    int remainingStock = stockQuantity - quantity;
    if (remainingStock < 0) {
      throw new NotEnoughStcokException("재고 수량이 부족합니다.");
    }
    stockQuantity = remainingStock;
  }
}
