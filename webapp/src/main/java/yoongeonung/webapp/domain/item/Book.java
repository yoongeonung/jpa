package yoongeonung.webapp.domain.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
public class Book extends Item{

  @Column(length = 50)
  private String author;
  @Column(length = 50)
  private String isbn;

}
