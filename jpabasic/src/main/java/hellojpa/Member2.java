package hellojpa;

import javax.persistence.*;

@Entity
public class Member2 {
    @Id
    @Column(name = "member2_id")
    private Long id;
    private String name;
    @Embedded // 값 타입을 사용하는곳에 선언
    private Period workPeriod;
    @Embedded // 값 타입을 사용하는곳에 선언
    @AttributeOverrides({
            @AttributeOverride(name = "startDate", column = @Column(name = "school_start")),
            @AttributeOverride(name = "endDate", column = @Column(name = "school_finish"))
    })
    private Period schoolPeriod;
    @Embedded // 값 타입을 사용하는곳에 선언
    private Address homeAddress;
}
