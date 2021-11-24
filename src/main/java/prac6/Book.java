package prac6;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;

@DiscriminatorValue("B")
public class Book extends Item{
    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "ISBN")
    private String isbn;
}
