package yoongeonung.practical01;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void save() {
        Member member = new Member();
        member.setUsername("TestUser");

        Long savedMemberId = memberRepository.save(member);

        Member foundMember = memberRepository.findById(savedMemberId);

        assertThat(foundMember).isEqualTo(member); // equals 비교
        assertThat(foundMember).isSameAs(member); // == 비교
        assertThat(foundMember.getId()).isEqualTo(member.getId());
        assertThat(foundMember.getUsername()).isEqualTo(member.getUsername());
    }
}