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
        Book book = Book.updateBook(form.getId(), form.getAuthor(), form.getIsbn(), form.getName(), form.getPrice(), form.getStockQuantity());
        itemService.saveItem(book);
        return "redirect:/items";
    }
}
