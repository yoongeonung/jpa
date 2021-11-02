package multiplerelationship;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MEMBER")
@Getter @Setter
public class Member {
    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CITY")
    private String city;
    @Column(name = "ZIPCODE")
    private String zipcode;
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
