package inheritancemapping;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MOVIE")
@DiscriminatorValue("M")
@NoArgsConstructor
public class Movie extends Item {
    @Column(name = "DIRECTOR")
    private String director;
    @Column(name = "ACTOR")
    private String actor;

    public Movie(String name, Integer price, String director, String actor) {
        super(name, price);
        this.director = director;
        this.actor = actor;
    }
}
