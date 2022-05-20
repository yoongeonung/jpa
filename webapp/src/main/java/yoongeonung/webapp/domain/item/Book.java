package yoongeonung.webapp.domain.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
public class Book extends Item{

  @Column(length = 50)
  private String author;
  @Column(length = 50)
  private String isbn;

  public static Book create(String bookname, int price, int quantity) {
    Book book = new Book();
    book.setName(bookname);
    book.setPrice(price);
    book.setStockQuantity(quantity);
    return book;
  }

}
