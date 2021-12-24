package jp.ac.hal.yoongeonung.jpa_practice1.controller;

import jp.ac.hal.yoongeonung.jpa_practice1.domain.Item.Item;
import jp.ac.hal.yoongeonung.jpa_practice1.domain.Member;
import jp.ac.hal.yoongeonung.jpa_practice1.domain.Order;
import jp.ac.hal.yoongeonung.jpa_practice1.repository.OrderSearch;
import jp.ac.hal.yoongeonung.jpa_practice1.service.ItemService;
import jp.ac.hal.yoongeonung.jpa_practice1.service.MemberService;
import jp.ac.hal.yoongeonung.jpa_practice1.service.OrderService;
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
public class OrderController {

    private final ItemService itemService;
    private final MemberService memberService;
    private final OrderService orderService;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Item> items = itemService.findItems();
        List<Member> members = memberService.findMembers();
        model.addAttribute("items", items);
        model.addAttribute("members", members);
        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@ModelAttribute OrderFormDTO orderFormDTO) {
        /**
         * 핵심 비즈니스 로직의 경우 컨트롤러보다는 서비스에서
         * 트랜잭션안에서 영속성 컨텍스트 안에서 로직을 실행하는게 좋다.
         */

        /**
         * DTO와 Form
         * 사실 Form이나 DTO나 모두 단순히 계층간에 데이터를 전달할 때 사용합니다. 그래서 둘의 역할은 똑같습니다.
         * 다만 form이라는 것은 제약을 더 두어서 명확하게 컨트롤러 까지만 사용해야 한다는 의미를 강하게 두었습니다.
         * form이라는 것 자체가 웹 기술에 종속적인 단어이니까요.
         * DTO이름 그대로 데이터 전송 객체인데, 더 범용적으로 사용되는 단어라 생각하시면 됩니다.
         * DTO는 어디에 정의해두는가에 따라 다르겠지만, 서비스에서도 사용할 수 있고, 리포지토리에서도 사용할 수 있습니다.
         */
        orderService.order(orderFormDTO);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancleOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
