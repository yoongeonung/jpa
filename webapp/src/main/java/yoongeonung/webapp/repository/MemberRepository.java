package yoongeonung.webapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import yoongeonung.webapp.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

  List<Member> findByName(String name);
}
