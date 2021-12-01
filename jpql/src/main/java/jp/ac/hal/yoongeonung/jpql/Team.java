package jp.ac.hal.yoongeonung.jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TEAM")
@Getter
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private Long id;

    @Column(name = "NAME")
    @Setter
    private String name;

    @OneToMany(mappedBy = "team")
    @Setter
    private List<Member> member = new ArrayList<>();
}
