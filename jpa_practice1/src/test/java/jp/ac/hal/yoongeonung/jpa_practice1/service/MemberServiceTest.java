package jp.ac.hal.yoongeonung.jpa_practice1.service;

import jp.ac.hal.yoongeonung.jpa_practice1.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원가입")
    void join() {
        //given
        Member member = new Member();
        member.setName("Line");
        //when
        Long savedId = memberService.join(member);
        //then
        assertEquals(member, memberService.findOne(savedId));
        Assertions.assertThat(member).isEqualTo(memberService.findOne(savedId));

    }

    @Test
    @DisplayName("중복회원예외")
    void duplicateException() {
        //given
        Member member1 = new Member();
        member1.setName("Kakao");
        Member member2 = new Member();
        member2.setName("Kakao");
        //when
        memberService.join(member1);
        //then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

}