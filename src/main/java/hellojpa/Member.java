package hellojpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member {
    // identity 전략 -> persist시 쿼리를 날려버린다. jpa가 id값을 알아야하기 때문에
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;
    public Member(String name) {
        this.name = name;
    }
}
