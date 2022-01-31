package hellojpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor // JPA는 기본생성자를 이용한다.
@Table(name = "member") // 테이블의 이름을 지정
@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR", sequenceName = "MEMBER_SEQ", initialValue = 1,allocationSize = 50)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "MEMBER_SEQ_GENERATOR")
    private Long id;
    @Column(name = "name")
    private String username;
    private byte age;
    @Enumerated(EnumType.STRING)
    private RoleType role_type;
    private LocalDate created_date;
    private LocalDate last_modified_date;
    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID") // FK를 가지고있는쪽이 연관관계의 주인이 된다.
    private Team team;

    public Member(String username, byte age, RoleType role_type, LocalDate created_date, LocalDate last_modified_date, String description) {
        this.username = username;
        this.age = age;
        this.role_type = role_type;
        this.created_date = created_date;
        this.last_modified_date = last_modified_date;
        this.description = description;
    }
}
