package yoongeonung.webapp.repository;

import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import yoongeonung.webapp.domain.Order;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

  private final EntityManager em;

  public void save(Order order) {
    em.persist(order);
  }

  public Order findOne(Long id) {
    return em.find(Order.class, id);
  }

//  public List<Order> findAll(OrderSearch orderSearch) { ... }

}
