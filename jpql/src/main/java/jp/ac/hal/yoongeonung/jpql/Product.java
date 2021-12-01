package jp.ac.hal.yoongeonung.jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@Getter
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;
    @Column(name = "NAME")
    @Setter
    private String name;
    @Column(name = "PRICE")
    @Setter
    private int price;
    @Column(name = "STOCKQUANTITY")
    @Setter
    private int stockQuantity;
}
