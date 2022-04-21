package jpabasic;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter
@DiscriminatorColumn(name = "dtype")
public class Item {
    @Id
    @Column(name = "item_id")
    private Long id;
    private String name;
    private Integer price;
}
