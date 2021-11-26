package prac5;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "CATEGORY_ITEM")
public class CategoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ITEM_ID")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY) // default EAGER -> LAZY로 변경
    @JoinColumn(name = "ITEM_ID")
    private Item item;
    @ManyToOne(fetch = FetchType.LAZY) // default EAGER -> LAZY로 변경
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
}
