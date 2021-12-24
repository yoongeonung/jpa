package jp.ac.hal.yoongeonung.jpa_practice1.service;

import jp.ac.hal.yoongeonung.jpa_practice1.controller.OrderFormDTO;
import jp.ac.hal.yoongeonung.jpa_practice1.domain.Delivery;
import jp.ac.hal.yoongeonung.jpa_practice1.domain.Item.Item;
import jp.ac.hal.yoongeonung.jpa_practice1.domain.Member;
import jp.ac.hal.yoongeonung.jpa_practice1.domain.Order;
import jp.ac.hal.yoongeonung.jpa_practice1.domain.OrderItem;
import jp.ac.hal.yoongeonung.jpa_practice1.repository.ItemRepository;
import jp.ac.hal.yoongeonung.jpa_practice1.repository.MemberRepository;
import jp.ac.hal.yoongeonung.jpa_practice1.repository.OrderRepository;
import jp.ac.hal.yoongeonung.jpa_practice1.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(OrderFormDTO formDTO) {

        // 엔티티 조회
        Member member = memberRepository.findOne(formDTO.getMemberId());
        Item item = itemRepository.findOne(formDTO.getItemId());

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item,item.getPrice(), formDTO.getCount());

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);

        // 생성한 주문의 식별자를 반환
        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        // 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        // JPA가 dirty checking(변경감지)를 통해 바뀐부분의 update쿼리를 날려준다.
        order.cancel();
    }

      public List<Order> findOrders(OrderSearch orderSearch) {
          return orderRepository.findAll(orderSearch);
      }

    public Order findOne(Long id) {
        return orderRepository.findOne(id);
    }
}
