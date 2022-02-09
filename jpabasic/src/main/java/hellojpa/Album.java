package hellojpa;

import javax.persistence.*;

@Entity
@DiscriminatorValue("ALBUM")
public class Album extends Item{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String artist;
}
