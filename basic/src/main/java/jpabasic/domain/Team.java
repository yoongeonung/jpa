package jpabasic.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

@Entity
@Getter
@Table(name = "team")
@NoArgsConstructor
public class Team {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @OneToMany(mappedBy = "team")
  private List<Member> members = new ArrayList<>();

  public Team(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Team{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
