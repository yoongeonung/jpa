package prac5;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Getter
@Table(name = "BOOK")
@DiscriminatorValue("B")
public class Book extends Item{
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "ISBN")
    private String isbn;
}
