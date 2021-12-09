package jp.ac.hal.yoongeonung.jpa_practice1.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Album extends Item{
    private String artist;
    private String etc;
}
