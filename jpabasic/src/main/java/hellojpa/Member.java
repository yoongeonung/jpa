package hellojpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor // JPA는 기본생성자를 이용한다.
@Table(name = "member") // 테이블의 이름을 지정
public class Member {
    @Id
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
    // only in memory
//    @Transient
//    private String temp;
}
