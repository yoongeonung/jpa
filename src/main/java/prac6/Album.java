package prac6;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;

@DiscriminatorValue("A")
public class Album extends Item{
    @Column(name = "ARTIST")
    private String artist;

    @Column(name = "ETC")
    private String etc;
}
