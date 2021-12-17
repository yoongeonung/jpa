package jp.ac.hal.yoongeonung.jpa_practice1.controller;

import jp.ac.hal.yoongeonung.jpa_practice1.domain.Item.Book;
import jp.ac.hal.yoongeonung.jpa_practice1.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        BookForm bookForm = new BookForm();
        model.addAttribute("form", bookForm);
        return "/items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form) {
        Book book = Book.createNewBook(form.getAuthor(), form.getIsbn(), form.getName(), form.getPrice(), form.getStockQuantity());
        itemService.saveItem(book);
        return "redirect:/";
    }
}
