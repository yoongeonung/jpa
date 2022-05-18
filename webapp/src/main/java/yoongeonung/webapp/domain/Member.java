package yoongeonung.webapp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;
  @Column(length = 50)
  @NotEmpty
  private String name;
  @Embedded
  @JsonProperty
  private Address address;

  @OneToMany(mappedBy = "member")
  private List<Order> orders = new ArrayList<>();

  private Member(String name, Address address) {
    this.name = name;
    this.address = address;
  }

  // create Member
  public static Member create(String name, Address address) {
    return  new Member(name, address);
  }

  @Override
  public String toString() {
    return "Member{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", address=" + address +
        '}';
  }
}
