package jp.ac.hal.yoongeonung.jpa_practice2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "DELIVERY")
@Getter
@Setter
public class Delivery {
    @Id
    @Column(name = "DELIVERY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    @JsonIgnore // 양방향 매핑의 경우 json매핑 한쪽을 끊어주지 않을경우 무한루프에 빠진다.
    private Order order;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
