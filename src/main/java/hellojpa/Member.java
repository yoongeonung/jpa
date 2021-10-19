package hellojpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    private LocalDate createDate;
    private LocalDateTime lastModifiedDate;
    @Lob
    private String description;

    public Member(String name) {
        this.name = name;
    }
}
