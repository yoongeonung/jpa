package yoongeonung.jpa_practice2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import yoongeonung.jpa_practice2.domain.item.Item;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
@Getter @Setter
public class OrderItem {
    @Id
    @Column(name = "ORDER_ITEM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @JsonIgnore // 양방향 매핑의 경우 json매핑 한쪽을 끊어주지 않을경우 무한루프에 빠진다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Column(name = "ORDERPRICE")
    private int orderPrice;

    @Column(name = "COUNT")
    private int count;

    //==생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    //==비즈니스 로직==//
    public void cancel() {
        getItem().addStock(count);
    }

    //==조회 로직==//

    /**
     * 주문상품 전체 가격 조회
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
