package jp.ac.hal.yoongeonung.jpa_practice1.repository;

import jp.ac.hal.yoongeonung.jpa_practice1.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
