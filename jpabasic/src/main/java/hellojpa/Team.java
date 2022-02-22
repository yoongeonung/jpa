package hellojpa;

import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private Long id;
    @Setter
    private String name;

    /**
     * mappedBy -> 연관관계의 주인을 말해준다. 나는 team에 의해 관리가 되고있고, 나는 읽기전용이다.
     */
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
}
