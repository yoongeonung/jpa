package yoongeonung.webapp.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
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

  @Test @Rollback(value = false)
  public void join() {
    //given
    Member testMember = Member.create("testMember", new Address());
    //when
    Long id = memberService.join(testMember);
    Member findMember = memberService.findMember(id);
    //then
    Assert.assertEquals(testMember, findMember);
  }

}