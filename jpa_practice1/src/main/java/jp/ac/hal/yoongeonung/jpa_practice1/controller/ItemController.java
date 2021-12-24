package jp.ac.hal.yoongeonung.jpa_practice1.controller;

import jp.ac.hal.yoongeonung.jpa_practice1.domain.Item.Book;
import jp.ac.hal.yoongeonung.jpa_practice1.domain.Item.Item;
import jp.ac.hal.yoongeonung.jpa_practice1.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        BookForm bookForm = new BookForm();
        model.addAttribute("form", bookForm);
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form) {
        Book book = Book.createNewBook(form.getAuthor(), form.getIsbn(), form.getName(), form.getPrice(), form.getStockQuantity());
        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId, Model model) {
        Item item = itemService.findOne(itemId);
        if (item instanceof Book) {
            Book book = (Book) item;
            BookForm bookForm = new BookForm();
            bookForm.setAuthor(book.getAuthor());
            bookForm.setId(book.getId());
            bookForm.setIsbn(book.getIsbn());
            bookForm.setName(book.getName());
            bookForm.setPrice(book.getPrice());
            bookForm.setStockQuantity(book.getStockQuantity());
            model.addAttribute("form", bookForm);
        }
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@ModelAttribute BookForm form) {
        /**
         * 사용하기 쉬운 메서드가 무엇인가? 라는 질문에 대한 답은 '파라미터가 필요 없는 메서드' 입니다. 그 다음은 '파라미터가 1개만 필요한 메서드'입니다. 이처럼 파라미터의 갯수가 많아지는 것은 그 메서드를 사용하기 힘들게 합니다.
         */
        // 여기서는 BookForm을 그대로사용하고있지만 왠만하면 DTO로 따로 분리하는게 좋다. 또한 DTO는 패키지 참조에 대해 신경써야 한다.
        // UpdateItemDTO라면 Item패키지 안에 두어 다른 패키지에서 참조되는걸 피하는게 좋다.
//        Book book = Book.updateBook(form.getId(), form.getAuthor(), form.getIsbn(), form.getName(), form.getPrice(), form.getStockQuantity());
//        itemService.saveItem(book);
        itemService.updateItem(form);
        return "redirect:/items";
    }
}
