package yoongeonung.webapp.repository;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import yoongeonung.webapp.domain.Member;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

  private EntityManager entityManager;

  public void save(Member member) {
    entityManager.persist(member);
  }

  //  TODO 2. 옵셔널 적용해보기
  public Member findOne(Long id) {
    return entityManager.find(Member.class, id);
  }

  public List<Member> findAll() {
    return entityManager.createQuery("select m from Member m", Member.class).getResultList();
  }

  //  TODO 1. 페이징 적용해보기
  public List<Member> findByName(String name) {
    return entityManager.createQuery("select m from Member m where m.name = :name", Member.class)
        .setParameter("name", name)
        .getResultList();
  }
}
