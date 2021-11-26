package inheritancemapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ALBUM")
public class Album extends Item {
    @Column(name = "ARTIST")
    private String artist;
}
