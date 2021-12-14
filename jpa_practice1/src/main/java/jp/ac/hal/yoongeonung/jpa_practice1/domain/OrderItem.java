package jp.ac.hal.yoongeonung.jpa_practice1.domain;

import jp.ac.hal.yoongeonung.jpa_practice1.domain.Item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "order_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 코드는 항상 제약하는 스타일로 작성하는게 좋다.
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    private int orderPrice; // 주문 당시의 가격
    private int count; // 주문 당시의 수량

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    // 생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.decreaseStock(count);
        return orderItem;
    }

//    protected OrderItem() {
//    }

    // 비즈니스 로직

    /**
     * 주문 취소
     */
    public void cancel() {
        item.addStock(count);
    }

    /**
     * 조회
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
