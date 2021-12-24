package jp.ac.hal.yoongeonung.jpa_practice1.service;

import jp.ac.hal.yoongeonung.jpa_practice1.controller.BookForm;
import jp.ac.hal.yoongeonung.jpa_practice1.domain.Item.Book;
import jp.ac.hal.yoongeonung.jpa_practice1.domain.Item.Item;
import jp.ac.hal.yoongeonung.jpa_practice1.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(BookForm form) {
        // 코드를 작성하는게 귀찮더라도 merge 보다는
        // 아래와 같이 변경감지를 사용하는것이 옳다.
        // 트랜잭션 안에서 이 코드가 실행되고 마지막에 commit이 호출 되면서
        // 변경감지가 일어나고 flush되면서 반영된다.
        // @DynamicUpdate 옵션이 엔티티에있으면 변경된 부분만 SQL을 만들어서 전달합니다.
        Item item = itemRepository.findOne(form.getId());
        if (item instanceof Book) {
            Book book = (Book) item;
            book.updateBook(form);
        }
    }


    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long id){
        return itemRepository.findOne(id);
    }
}
