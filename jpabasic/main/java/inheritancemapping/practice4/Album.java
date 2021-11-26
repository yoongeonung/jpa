package inheritancemapping.practice4;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ALBUM")
@DiscriminatorValue("A")
public class Album extends Item {
    @Column(name = "ARTIST")
    private String artist;
    @Column(name = "ETC")
    private String etc;
}
