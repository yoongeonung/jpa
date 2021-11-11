package proxy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TEAM")
@Getter @Setter
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Transient
    private Member member;
}
