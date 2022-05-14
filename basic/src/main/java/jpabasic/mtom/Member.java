package jpabasic.mtom;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "MEMBER")
public class Member {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "MEMBER_ID")
  private Long id;
  @Column(name = "USERNAME", length = 50)
  @Setter
  private String username;

  @ManyToMany
  @Setter
  private List<Product> products = new ArrayList<>();

}
