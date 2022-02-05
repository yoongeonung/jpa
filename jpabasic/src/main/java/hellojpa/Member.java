package hellojpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor // JPA는 기본생성자를 이용한다.
@Table(name = "member", uniqueConstraints = {@UniqueConstraint(name = "member_locker", columnNames = {"locker_id"})}) // 테이블의 이름을 지정
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String username;
    private byte age;
    @Enumerated(EnumType.STRING) // varchar매핑됨
    private RoleType role_type;
    private LocalDate created_date;
    private LocalDate last_modified_date;
    @Lob
    private String description;

    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker locker;

    public Member(String username, byte age, RoleType role_type, LocalDate created_date, LocalDate last_modified_date, String description) {
        this.username = username;
        this.age = age;
        this.role_type = role_type;
        this.created_date = created_date;
        this.last_modified_date = last_modified_date;
        this.description = description;
    }
}
