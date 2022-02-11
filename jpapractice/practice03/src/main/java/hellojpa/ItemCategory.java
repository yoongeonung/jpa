package hellojpa;

import javax.persistence.*;

@Entity
@Table(name = "ITEM_CATEGORY")
public class ItemCategory {
    @Id @Column(name = "ITEM_CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
}
