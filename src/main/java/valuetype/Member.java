package valuetype;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MEMBER")
@Getter @Setter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Embedded
    private Period workPeriod;

    @Embedded
    private Address workAddress;

    @Embedded
    @AttributeOverrides({
                    @AttributeOverride(name = "city", column = @Column(name = "HOME_CITY")),
                    @AttributeOverride(name = "street", column = @Column(name = "HOME_STREET")),
                    @AttributeOverride(name = "zipcode", column = @Column(name = "HOME_ZIPCODE"))
    })
    private Address homeAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "member")
    private List<AddressEntity> addressHistory = new ArrayList<>();
}
