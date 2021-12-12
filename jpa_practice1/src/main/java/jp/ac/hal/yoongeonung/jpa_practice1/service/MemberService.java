package jp.ac.hal.yoongeonung.jpa_practice1.service;

import jp.ac.hal.yoongeonung.jpa_practice1.domain.Member;
import jp.ac.hal.yoongeonung.jpa_practice1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
/*
클래스 레벨에 선언할 경우 public인 메서드들에 걸려들어간다.
조회의 경우 @Transactional(readOnly = true)를 사용할경우
JPA가 조금더 최적화를 해준다. but 변경이 있는곳에 readOnly=true를 사용할 경우
변경이 일어나지 않는다.
 */
@Transactional(readOnly = true) // spring의 트랜잭션을 사용하는게 좋다.
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional // 클래스레벨이 아닌 따로 메서드에 설정한건 이쪽이 우선권을 가진다.
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    private void validateDuplicateMember(Member member) {
        List<Member> members = memberRepository.findByName(member.getName());
        if (members.size() > 0) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
