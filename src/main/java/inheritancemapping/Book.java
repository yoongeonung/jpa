package inheritancemapping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BOOK")
public class Book extends Item{
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "ISBN")
    private String ISBN;
}
