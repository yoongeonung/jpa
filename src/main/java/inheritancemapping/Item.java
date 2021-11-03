package inheritancemapping;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn // 기본값 = "DTYPE"
@Table(name = "ITEM")
@NoArgsConstructor
@Setter @Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private Integer price;
    public Item(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
