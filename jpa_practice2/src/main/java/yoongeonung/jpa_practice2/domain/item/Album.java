package yoongeonung.jpa_practice2.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("A")
@Entity
@Getter
@Setter
public class Album extends Item{
    @Column(name = "ARTIST")
    private String artist;
    @Column(name = "ETC")
    private String etc;
}
