package yoongeonung.webapp.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yoongeonung.webapp.domain.Member;
import yoongeonung.webapp.repository.OldMemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

  private final OldMemberRepository memberRepository;

  @Transactional
  public Long join(Member member) {
    validateDuplicateMember(member);
    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(Member member) {
    List<Member> members = memberRepository.findByName(member.getName());
    if (members.size() >= 1) {
      throw new IllegalStateException("이미 존재하는 회원입니다");
    }
  }

  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  public Member findMember(Long id) {
    return memberRepository.findOne(id);
  }

  @Transactional
  public void update(Long id, String name) {
    // Merge보다 DL
    Member member = memberRepository.findOne(id);
    member.setName(name);
  }
}
