package valuetype;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESSENTITY_ID")
    private Long id;

    private Address address;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
