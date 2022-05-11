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
import yoongeonung.webapp.domain.CategoryItem;

@Entity
@Inheritance
@DiscriminatorColumn
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
}