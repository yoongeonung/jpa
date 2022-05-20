package yoongeonung.webapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import yoongeonung.webapp.domain.item.Item;

@Entity
@Getter @Setter(value = AccessLevel.PRIVATE)
@Table(name = "orders_item")
public class OrderItem {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "orders_item_id")
  private Long id;

  private int orderPrice;
  private int count;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id",foreignKey = @ForeignKey(name = "fk_orderitem_item"))
  private Item item;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "orders_id", foreignKey = @ForeignKey(name = "fk_orderitem_order"))
  private Order order;

  public void relateOrder(Order order) {
    this.order = order;
  }

  public void relateItem(Item item) {
    this.item = item;
  }

  public static OrderItem create(Item item, int orderPrice, int count) {
    OrderItem orderItem = new OrderItem();
    orderItem.relateItem(item);
    orderItem.setOrderPrice(orderPrice);
    orderItem.setCount(count);

    item.decreaseStock(count);
    return orderItem;
  }

  public void cancel() {
    getItem().increaseStock(count);
  }

  public int getTotalPrice() {
    return getOrderPrice() * getCount();
  }
}
