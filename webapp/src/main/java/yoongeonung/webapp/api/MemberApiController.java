package yoongeonung.webapp.api;

import javax.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yoongeonung.webapp.domain.Member;
import yoongeonung.webapp.service.MemberService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberApiController {

  private final MemberService memberService;

  @PostMapping("/api/v1/member")
  public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member,
      BindingResult result) {
    log.info("member = {} ", member);
    log.info("result = {} ", result);
    Long id = memberService.join(member);
    return new CreateMemberResponse(id);
  }


  @Data
  static class CreateMemberResponse {

    private Long id;

    public CreateMemberResponse(Long id) {
      this.id = id;
    }
  }
}
