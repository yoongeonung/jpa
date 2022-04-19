package jpabasic;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity // JPAが管理するオブジェクト
@Table(name = "member")
@Getter @Setter
public class Member {
    @Id
    @Column(name = "member_id")
    private Long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker locker;
}
