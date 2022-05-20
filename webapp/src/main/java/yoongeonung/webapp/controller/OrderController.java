package yoongeonung.webapp.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yoongeonung.webapp.domain.Member;
import yoongeonung.webapp.domain.Order;
import yoongeonung.webapp.domain.OrderSearch;
import yoongeonung.webapp.domain.item.Item;
import yoongeonung.webapp.service.ItemService;
import yoongeonung.webapp.service.MemberService;
import yoongeonung.webapp.service.OrderService;

@Controller
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;
  private final MemberService memberService;
  private final ItemService itemService;

  @GetMapping(value = "/order")
  public String createForm(Model model) {
    List<Member> members = memberService.findMembers();
    List<Item> items = itemService.findItems();
    model.addAttribute("members", members);
    model.addAttribute("items", items);
    return "order/orderForm";
  }

  @PostMapping(value = "/order")
  public String order(@RequestParam("memberId") Long memberId,
      @RequestParam("itemId") Long itemId, @RequestParam("count") int count) {
    orderService.order(memberId, itemId, count);
    return "redirect:/orders";
  }

  @GetMapping(value = "/orders")
  public String orderList(@ModelAttribute("orderSearch") OrderSearch
      orderSearch, Model model) {
    List<Order> orders = orderService.findOrders(orderSearch);
    model.addAttribute("orders", orders);
    return "order/orderList";
  }

  @PostMapping(value = "/orders/{orderId}/cancel")
  public String cancelOrder(@PathVariable("orderId") Long orderId) {
    orderService.cancel(orderId);
    return "redirect:/orders";
  }
}
