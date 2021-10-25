package hellojpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "TEAM_ID") // Member table의 FK와 매핑
    private Team team; // 연관관계의 주인
    public Member(String name) {
        this.name = name;
    }

    public void addTeam(Team team) {
        team.getMembers().add(this);
        this.team = team;
    }
}
