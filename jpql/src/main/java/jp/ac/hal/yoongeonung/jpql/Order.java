package jp.ac.hal.yoongeonung.jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS")
@Getter
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDERS_ID")
    private Long id;

    @Column(name = "ORDERAMOUNT")
    @Setter
    private int orderAmount;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    @Setter
    private Member member;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
