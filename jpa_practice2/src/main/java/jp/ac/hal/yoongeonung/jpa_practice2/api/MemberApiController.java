package jp.ac.hal.yoongeonung.jpa_practice2.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import jp.ac.hal.yoongeonung.jpa_practice2.domain.Address;
import jp.ac.hal.yoongeonung.jpa_practice2.domain.Member;
import jp.ac.hal.yoongeonung.jpa_practice2.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 절대로 엔티티를 외부로 노출시켜서는 안된다.
     * DTO나 VO를 사용하여 반환하는게 좋다.
     * 엔티티의 변경사항이 생기면 API스펙이 바뀌어버려
     * 큰 장애의 원인이 된다.
     * 또 한 엔티티의 정보가 모두 노출되기때문에
     * 보안상 좋지않다.
     */
    @GetMapping("/api/v1/members")
    public List<Member> membersV1() {
        return memberService.findAll();
    }

    @GetMapping("/api/v2/members")
    public Result membersV2() {
        List<MemberDTO> collect = memberService.findAll().stream().map(
                m -> new MemberDTO(m.getName(), m.getAddress().getCity(), m.getAddress().getStreet(), m.getAddress().getZipcode())
        ).collect(Collectors.toList());
        return new Result(collect.size(),collect);
    }

    /**
     * 등록 V1: 요청 값으로 Member 엔티티를 직접 받는다.
     * 문제점
     * - 엔티티에 프레젠테이션 계층을 위한 로직이 추가된다.
     * - 엔티티에 API 검증을 위한 로직이 들어간다. (@NotEmpty 등등)
     * - 실무에서는 회원 엔티티를 위한 API가 다양하게 만들어지는데, 한 엔티티에 각각의 API를
     * 위한 모든 요청 요구사항을 담기는 어렵다.
     * - 엔티티가 변경되면 API 스펙이 변한다.
     * 결론
     * - API 요청 스펙에 맞추어 별도의 DTO를 파라미터로 받는다.
     */
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody Member member) {
        Long memberId = memberService.join(member);
        return new CreateMemberResponse(memberId);
    }

    /**
     * 등록 V2: 요청 값으로 Member 엔티티 대신에 별도의 DTO를 받는다.
     * DTO를 별도로 받는것이 API명세를 건드리지 않으며 사이드 이펙트롤 유발하지 않는다.
     * 엔티티는 엔티티로서 변경되어도 API 스펙에 영향을 끼치지 않는다.
     * 또한 DTO를 검증(@Valid)함으로써 API스펙자체를 DTO만 보고도 알수있다.
     */
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest createMemberRequest) {
        Member member = new Member();
        member.setName(createMemberRequest.getName());
        member.setAddress(new Address(createMemberRequest.getAddress().getCity(),createMemberRequest.getAddress().getStreet(), createMemberRequest.getAddress().getZipcode()));
//        member.setAddress(new Address(createMemberRequest.getCity(),createMemberRequest.getStreet(),createMemberRequest.getZipcode()));
        Long joinedId = memberService.join(member);
        return new CreateMemberResponse(joinedId);
    }

    /**
     * 수정 API
     */
    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable Long id, @RequestBody @Valid UpdateMemberRequest updateMemberRequest) {
        // command
        memberService.update(id, updateMemberRequest.getName());
        // query , command와 쿼리는 분리해야한다.
        Member member = memberService.findOne(id);
        return new UpdateMemberResponse(member.getId(), member.getName());
    }


    /**
     * 밖에서 사용하지 않는 DTO를 굳이 밖에다 만들 필요는 없다
     * 이 클래스 안에서만 사용한다는 의미로
     * inner class로 만들어줘도 충분하다.
     */
    @Data
    @AllArgsConstructor
    static class CreateMemberResponse{
        /**
         * @JsonIgnore 컨버터에의해 json으로 변환되는것을 방지
         */
        //@JsonIgnore
        private Long id;
    }

    @Data
    static class CreateMemberRequest {
        @NotEmpty
        private String name;
        //        @NotEmpty
//        private String city;
//        @NotEmpty
//        private String street;
//        @NotEmpty
//        private String zipcode;
        @JsonProperty("address")
        private Address address;
    }

    @Data
    private static class UpdateMemberRequest {
        @NotEmpty
        private String name;
    }

    @Data
    @AllArgsConstructor
    private static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    private static class Result<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    private static class MemberDTO {
        private String name;
        private String city;
        private String street;
        private String zipcode;
    }
}
