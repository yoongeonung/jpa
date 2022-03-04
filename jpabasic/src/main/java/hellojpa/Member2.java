package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
public class Member2 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns = @JoinColumn(name = "member2_id"))
    @Column(name = "FOOD_NAME") // 값이 하나고, 직접 선언한 값타입이 아니기 때문에 예외적 허용
    private Set<String> favoriteFoods = new HashSet<>();

//    @ElementCollection
//    @CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "member2_id"))
//    private List<Address> addressHistory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // 값 타입을 엔티티로 승격시켜 값타잆처럼 사용
    @JoinColumn(name = "member2_id") // 1쪽에서 FK를 관리
    private List<AddressEntity> addressHistory = new ArrayList<>();
}
