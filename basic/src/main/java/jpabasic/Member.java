package jpabasic;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity // JPAが管理するオブジェクト
@Table(name = "member")
@Getter @Setter
public class Member {
    @Id
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team team;
}
