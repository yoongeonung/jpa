package yoongeonung.webapp;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

  @Autowired
  private MemberRepository memberRepository;

  @Test
  @Transactional
  public void save() {
      //given
    Member member = new Member("testMember");
    //when
    Long savedMemberId = memberRepository.save(member);
    Member findMember = memberRepository.findById(member.getId());
    //then
    assertThat(savedMemberId).isEqualTo(member.getId());
    assertThat(findMember).isEqualTo(member);
    assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
  }
}