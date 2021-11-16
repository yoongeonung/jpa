package prac5;


import javax.persistence.*;

@Entity
@Table(name = "ITEM")
@DiscriminatorColumn // default DTYPE
@Inheritance(strategy = InheritanceType.JOINED) // JOIN 전략
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private int price;
    @Column(name = "STOCKQUANTITY")
    private int stockQuantiy;
}
