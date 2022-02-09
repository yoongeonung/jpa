package hellojpa;

import javax.persistence.*;

@Entity
@DiscriminatorValue("MOVIE")
public class Movie extends Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String director;
    private String actor;
}
