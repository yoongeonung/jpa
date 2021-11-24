package prac6;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;

@DiscriminatorValue("M")
public class Movie extends Item{
    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "ACTOR")
    private String actor;
}
