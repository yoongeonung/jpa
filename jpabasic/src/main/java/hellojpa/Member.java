package hellojpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor // JPA는 기본생성자를 이용한다.
@Table(name = "member") // 테이블의 이름을 지정
public class Member {
    @Id
    private Long id;
    private String name;
}
