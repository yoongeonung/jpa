package jpabasic;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter @Setter
@DiscriminatorColumn
public class Item extends BaseEntity{
    @Id
    @Column(name = "item_id")
    private Long id;
    private String name;
    private Integer price;
}
