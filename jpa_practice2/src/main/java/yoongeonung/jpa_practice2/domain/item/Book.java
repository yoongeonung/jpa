package yoongeonung.jpa_practice2.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
@Setter
public class Book extends Item{
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "ISBN")
    private String isbn;
}
