package jp.ac.hal.yoongeonung.jpa_practice1.controller;

import jp.ac.hal.yoongeonung.jpa_practice1.domain.Address;
import jp.ac.hal.yoongeonung.jpa_practice1.domain.Member;
import jp.ac.hal.yoongeonung.jpa_practice1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result) {
        if (result.hasErrors()) {
            // BindingResult : @Valid에서 나온 에러들을 담아서 바로 튕기는게 아니라 처리할수 있게 해준다.
            // BindingResult에 바인딩된 검증 에러들을 스프링이 같이 템플레이트에 넘겨준다.
            return "members/createMemberForm";
        }
        Member member = new Member();
        member.setAddress(new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode()));
        member.setName(memberForm.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        // 왠만하면 직접 엔티티를 사용하기 보다는 DTO로 전환해서 보내는걸 추천한다.
        // 특히 api를 만들경우 절대 엔티티를 직접 반환하면 안된다.
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
