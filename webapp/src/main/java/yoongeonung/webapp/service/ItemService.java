package yoongeonung.webapp.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yoongeonung.webapp.domain.item.Item;
import yoongeonung.webapp.repository.ItemRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  @Transactional
  public void saveItem(Item item) {
    itemRepository.save(item);
  }

  public Item findItem(Long id) {
    return itemRepository.findItem(id);
  }

  public List<Item> findItems() {
    return itemRepository.findItems();
  }

  /**
   * 영속성 컨텍스트가 자동 변경
   */
  @Transactional
  public void updateItem(Long id, String name, int price) {
    Item item = itemRepository.findItem(id);
    item.setName(name);
    item.setPrice(price);
  }

}
