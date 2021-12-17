package jp.ac.hal.yoongeonung.jpa_practice1.domain.Item;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends Item {
    @Setter(value = AccessLevel.PRIVATE)
    private String author;
    @Setter(value = AccessLevel.PRIVATE)
    // static으로 만드는것을 고려?
    private String isbn;
    private String isbn2;

    public static Book createNewBook(String author, String isbn, String name, int price, int quantity) {
        Book book = new Book();
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(quantity);
        return book;
    }
}
