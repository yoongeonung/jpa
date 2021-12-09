package jp.ac.hal.yoongeonung.jpa_practice1.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category")
    private List<CategoryItem> categoryItems = new ArrayList<>();
}
