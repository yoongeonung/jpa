package yoongeonung.webapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yoongeonung.webapp.domain.Delivery;
import yoongeonung.webapp.domain.DeliveryStatus;
import yoongeonung.webapp.domain.Member;
import yoongeonung.webapp.domain.Order;
import yoongeonung.webapp.domain.OrderItem;
import yoongeonung.webapp.domain.item.Item;
import yoongeonung.webapp.repository.ItemRepository;
import yoongeonung.webapp.repository.MemberRepository;
import yoongeonung.webapp.repository.OrderRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

  private final MemberRepository memberRepository;
  private final ItemRepository itemRepository;
  private final OrderRepository orderRepository;

  @Transactional
  public Long order(Long memberId, Long itemId, int count) {
    Member member = memberRepository.findOne(memberId);
    Item item = itemRepository.findItem(itemId);

    Delivery delivery = Delivery.create(member.getAddress(), DeliveryStatus.READY);

    OrderItem orderItem = OrderItem.create(item, item.getPrice(), count);

    Order order = Order.create(member, delivery, orderItem);

    orderRepository.save(order);
    return order.getId();
  }

  public void cancel(Long orderId) {
    Order order = orderRepository.findOne(orderId);
    order.cancel();
  }

//  public List<Order> findOrders(OrderSearch orderSearch) {
//
//    return orderRepository.findAll(orderSearch);
//  }
}