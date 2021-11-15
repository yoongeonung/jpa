package prac5;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@Table(name = "MEMBER")
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql - auto_increment
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STREET")
    private String street;
    @Column(name = "ZIPCODE")
    private String zipcode;
    @OneToMany(mappedBy = "member") // 양방향 연관관계 매핑
    private List<Order> orders = new ArrayList<>();

    public Member(String name, String city, String street, String zipcode) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
