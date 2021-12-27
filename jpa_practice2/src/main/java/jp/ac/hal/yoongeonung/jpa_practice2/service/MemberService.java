package jp.ac.hal.yoongeonung.jpa_practice2.service;

import jp.ac.hal.yoongeonung.jpa_practice2.domain.Member;
import jp.ac.hal.yoongeonung.jpa_practice2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
