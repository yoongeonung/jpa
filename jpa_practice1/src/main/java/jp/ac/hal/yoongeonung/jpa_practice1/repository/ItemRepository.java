package jp.ac.hal.yoongeonung.jpa_practice1.repository;

import jp.ac.hal.yoongeonung.jpa_practice1.domain.Item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            // DB에저장이 되어서 식별자가 존재하는 상태 -> 준영속 상태 (item)
            // 병합이 되고 영속성 컨텍스트에 의해 관리 되는 상태 -> 영속 상태 (mergeItem)
            // 병합은 모든 필드를 교체한다. -> null로 돌아올 가능성이있다
            // 병합 보다는 변경감지를 사용해야한다.
            Item mergeItem = em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }
}
