package jp.ac.hal.yoongeonung.jpa_practice2.service;

import jp.ac.hal.yoongeonung.jpa_practice2.domain.Member;
import jp.ac.hal.yoongeonung.jpa_practice2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        // System.out.println(member.getId()); // null
        memberRepository.save(member);
        // System.out.println(member.getId()); // id = 1
        return member.getId();
    }

    @Transactional
    public void update(Long id, String name) {
        // DB -> 영속성컨텍스트에 올린다
        Member member = findOne(id);
        // 영속성 컨텍스트에 있는 member를 변경
        member.setName(name);
        // 메서드가 종료되는 시점(트랜잭션이 끝나는 시점)에 변경감지가 일어나서 update 쿼리가 날라간다.
    }

    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

}
