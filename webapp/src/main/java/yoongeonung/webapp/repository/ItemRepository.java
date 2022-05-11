package yoongeonung.webapp.repository;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import yoongeonung.webapp.domain.item.Item;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

  private final EntityManager entityManager;

  public void save(Item item) {
    if (item.getId() == null) {
      entityManager.persist(item);
    } else {
      entityManager.merge(item); // update?
    }
  }

  public Item findItem(Long id) {
    return entityManager.find(Item.class, id);
  }

  public List<Item> findItems() {
    return entityManager.createQuery("select i from Item i", Item.class).getResultList();
  }

}
