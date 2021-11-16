package prac5;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Getter
@Table(name = "ALBUM")
@DiscriminatorValue("A")
public class Album extends Item{
    @Column(name = "ARTIST")
    private String artist;
    @Column(name = "ETC")
    private String etc;
}
