package jp.ac.hal.yoongeonung.jpa_practice2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Embedded
    private Address address;
    @JsonIgnore // 양방향 매핑의 경우 json매핑 한쪽을 끊어주지 않을경우 무한루프에 빠진다.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
