package jp.ac.hal.yoongeonung.jpa_practice1.repository;

import jp.ac.hal.yoongeonung.jpa_practice1.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    // 단건 조회
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    //
    public List<Order> findAll(OrderSearch orderSearch) {
        // TODO 1. 쿼리dsl로 변환
        return em.createQuery("select o from Order o where o.status = :status and o.member.name = :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .getResultList();
    }
}
