package yoongeonung.webapp.service;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import yoongeonung.webapp.domain.Address;
import yoongeonung.webapp.domain.Member;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

  @Autowired
  private MemberService memberService;

  @Test
  public void join() {
    //given
    Member testMember = Member.create("testMember", new Address());
    //when
    Long id = memberService.join(testMember);
    Member findMember = memberService.findMember(id);
    //then
    Assert.assertEquals(testMember, findMember);
  }

  @Test(expected = IllegalStateException.class)
  public void duplicateMemberException() {

    Member member1 = Member.create("member", new Address());
    Member member2 = Member.create("member", new Address());

    memberService.join(member1);
    memberService.join(member2); // exception

    fail("예외가 발생해야 한다.");
  }

}