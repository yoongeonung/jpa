package inheritancemapping;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Album extends Item{
    @Column(name = "ARTIST")
    private String artist;
}
