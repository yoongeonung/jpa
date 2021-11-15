package prac5;


import javax.persistence.*;

// TODO 상속이 아닌 조립을 이용
@Entity
@Table(name = "ITEM")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private int stockQuantiy;
}
