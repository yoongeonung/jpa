package jp.ac.hal.yoongeonung.jpa_practice1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired private MemberRepository memberRepository;

    @Test
    @DisplayName("save test")
    @Transactional
    @Rollback(value = false)
    void save() {
        //given
        Member member = new Member();
        member.setUsername("Line");
        //when
        Long savedMemberId = memberRepository.save(member);
        Member foundedMember = memberRepository.findById(savedMemberId);
        //then
        Assertions.assertThat(member.getId()).isEqualTo(foundedMember.getId());
        Assertions.assertThat(member.getUsername()).isEqualTo(foundedMember.getUsername());
        // 영속성 컨텍스트안에 있기 때문에 같음을 보장 같은 식별자, select도 안나감
        Assertions.assertThat(member).isEqualTo(foundedMember);
    }
}