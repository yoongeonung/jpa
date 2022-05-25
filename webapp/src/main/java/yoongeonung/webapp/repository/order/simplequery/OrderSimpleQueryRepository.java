package yoongeonung.webapp.repository.order.simplequery;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderSimpleQueryRepository {

  private EntityManager em;

  public List<OrderSimpleQueryDto> findOrderDtos() {
    em.createQuery(
        "select new yoongeonung.webapp.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address) from Order o join fetch o.member m join fetch o.delivery d",
        OrderSimpleQueryDto.class).getResultList();
  }

}
