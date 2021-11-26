package prac5;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "MOVIE")
@DiscriminatorValue("M")
public class Movie extends Item {
    @Column(name = "DIRECTOR")
    private String director;
    @Column(name = "ACTOR")
    private String actor;
}
