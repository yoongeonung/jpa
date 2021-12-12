package jp.ac.hal.yoongeonung.jpa_practice1.repository;

import jp.ac.hal.yoongeonung.jpa_practice1.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
public class MemberRepository {
    /*
    @PersistenceContext를 사용하면
    EntityManager를 스프링이 주입해준다.
    factory가 필요할 경우
    @PersistenceUnit 를 사용하면 된다.
     */
    @PersistenceContext
    private EntityManager em;
    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name).getResultList();
    }
}
