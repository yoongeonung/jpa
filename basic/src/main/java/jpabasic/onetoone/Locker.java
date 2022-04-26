package jpabasic.onetoone;

import javax.persistence.*;

@Entity
@Table(name = "locker")
public class Locker {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locker_id")
    private Long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
