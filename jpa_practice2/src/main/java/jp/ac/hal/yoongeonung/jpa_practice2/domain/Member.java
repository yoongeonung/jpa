package jp.ac.hal.yoongeonung.jpa_practice2.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 절대로 엔티티를 외부로 노출시켜서는 안된다.
 * DTO나 VO를 사용하여 반환하는게 좋다.
 * 엔티티의 변경사항이 생기면 API스펙이 바뀌어버려
 * 큰 장애의 원인이 된다.
 * 또 한 엔티티의 정보가 모두 노출되기때문에
 * 보안상 좋지않다.
 */
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
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
