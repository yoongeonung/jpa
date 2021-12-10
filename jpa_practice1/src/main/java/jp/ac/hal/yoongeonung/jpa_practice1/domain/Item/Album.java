package jp.ac.hal.yoongeonung.jpa_practice1.domain.Item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
@Getter
@Setter
public class Album extends Item{
    private String artist;
    private String etc;
}
