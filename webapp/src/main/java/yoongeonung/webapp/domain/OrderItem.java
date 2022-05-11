package yoongeonung.webapp.domain;

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
import yoongeonung.webapp.domain.item.Item;

@Entity
@Table(name = "orders_item")
public class OrderItem {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "orders_item_id")
  private Long id;

  private int orderPrice;
  private double count;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id",foreignKey = @ForeignKey(name = "fk_orderitem_item"))
  private Item item;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "orders_id", foreignKey = @ForeignKey(name = "fk_orderitem_order"))
  private Order order;
}
