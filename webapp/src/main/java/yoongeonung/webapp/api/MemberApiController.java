package yoongeonung.webapp.api;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yoongeonung.webapp.domain.Address;
import yoongeonung.webapp.domain.Member;
import yoongeonung.webapp.service.MemberService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberApiController {

  private final MemberService memberService;

  @PostMapping("/api/v1/members")
  public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member,
      BindingResult result) {
    Long id = memberService.join(member);
    return new CreateMemberResponse(id);
  }

  @PostMapping("/api/v2/members")
  public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {

    Member member = Member.create(request.getName(), request.getAddress());
    Long id = memberService.join(member);

    return new CreateMemberResponse(id);
  }

  @PutMapping("/api/v2/members/{id}")
  public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
      @RequestBody @Valid UpdateMemberRequest request) {
    // update는 update로서 끝
    memberService.update(id, request.getName());
    Member findMember = memberService.findMember(id);
    return new UpdateMemberResponse(findMember.getId(), findMember.getName());
  }

  @GetMapping("/api/v1/members")
  public List<Member> membersV1() {
    return memberService.findMembers();
  }

  @GetMapping("/api/v2/members")
  public Result membersV2() {
    List<Member> members = memberService.findMembers();
    List<MemberDTO> collect = members.stream().map(m -> new MemberDTO(m.getName()))
        .collect(Collectors.toList());
    return new Result(collect);
  }


  @Data
  @AllArgsConstructor
  static class CreateMemberResponse {

    @NotEmpty
    private Long id;
  }

  @Data
  static class CreateMemberRequest {

    private String name;
    private Address address;
  }

  @Data
  @AllArgsConstructor
  static class UpdateMemberResponse {

    private Long id;
    private String name;
  }

  @Data
  static class UpdateMemberRequest {

    private String name;
  }

  @Data
  static class Result<T> {

    private T data;

    public Result(T data) {
      this.data = data;
    }
  }

  @Data
  static class MemberDTO {

    @NotEmpty
    private String name;

    public MemberDTO(String name) {
      this.name = name;
    }
  }
}
