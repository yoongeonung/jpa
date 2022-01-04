package jp.ac.hal.yoongeonung.jpa_practice2.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
public class Category {
    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<CategoryItem> categoryItems = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Category parent; // 자식입장

    @OneToMany(mappedBy = "parent")
    private List<Category> child; // 부모입장

    /**
     * 연관관계 메서드
     */
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}
