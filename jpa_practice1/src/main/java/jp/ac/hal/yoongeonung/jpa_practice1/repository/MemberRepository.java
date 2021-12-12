package jp.ac.hal.yoongeonung.jpa_practice1.repository;

import jp.ac.hal.yoongeonung.jpa_practice1.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    /*
    @PersistenceContext를 사용해야지만
    EntityManager를 스프링이 주입해준다.
    factory가 필요할 경우
    @PersistenceUnit 를 사용하면 된다.

    현시점 spring data jpa를 사용하면 @PersistenceContext가 아닌
    @Autowired를 사용해도 EntityManager를 주입해준다.
    즉 @RequiredArgsConstructor를 사용 할수 있다는 뜻
     */
    private final EntityManager em;

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
