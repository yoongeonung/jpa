package jp.ac.hal.yoongeonung.jpa_practice2.repository;

import jp.ac.hal.yoongeonung.jpa_practice2.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }
}
