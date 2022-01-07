package yoongeonung.jpa_practice2.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Getter
@Setter
public class Movie extends Item {
    @Column(name = "DIRECTOR")
    private String director;
    @Column(name = "ACTOR")
    private String actor;
}
