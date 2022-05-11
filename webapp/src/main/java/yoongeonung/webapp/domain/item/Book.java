package yoongeonung.webapp.domain.item;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Book extends Item{

  @Column(length = 50)
  private String author;
  @Column(length = 50)
  private String isbn;
}
