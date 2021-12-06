package jp.ac.hal.yoongeonung.jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter
@Table(name = "MEMBER")
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "NAME")
    @Setter
    private String username;

    @Column(name = "AGE")
    @Setter
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public void changeTeam(Team team) {
        this.team = team;
        team.getMember().add(this);
    }
}
