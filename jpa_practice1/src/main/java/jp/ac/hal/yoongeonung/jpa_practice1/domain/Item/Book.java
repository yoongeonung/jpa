package jp.ac.hal.yoongeonung.jpa_practice1.domain.Item;

import jp.ac.hal.yoongeonung.jpa_practice1.controller.BookForm;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends Item {
    @Setter(value = AccessLevel.PRIVATE)
    private String author;
    @Setter(value = AccessLevel.PRIVATE)
    // static으로 만드는것을 고려?
    private String isbn;

    public static Book createNewBook(String author, String isbn, String name, int price, int quantity) {
        Book book = new Book();
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(quantity);
        return book;
    }

    public void updateBook(BookForm form) {
        //Long id, String author, String isbn, String name, int price, int quantity
//        Book book = new Book();
//        book.setId(id);
//        book.setAuthor(author);
//        book.setIsbn(isbn);
//        book.setName(name);
//        book.setPrice(price);
//        book.setStockQuantity(quantity);
//        return book;
        this.setId(form.getId());
        this.setName(form.getName());
        this.setPrice(form.getPrice());
        this.setStockQuantity(form.getStockQuantity());
        this.setAuthor(form.getAuthor());
        this.setIsbn(form.getIsbn());
    }
}
