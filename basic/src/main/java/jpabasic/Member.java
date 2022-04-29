package jpabasic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import jpabasic.valuetype.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "MEMBER")
@NoArgsConstructor
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MEMBER_ID")
  private Long id;

  @ElementCollection
  @CollectionTable(
      name = "FAVORITE_FOOD",
      joinColumns = {
          @JoinColumn(name = "MEMBER_ID")
      }
  )
  @Column(name = "FOOD_NAME")
  private Set<String> favoriteFoods = new HashSet<>();

  @ElementCollection
  @CollectionTable(
      name = "ADDRESS",
      joinColumns = {
          @JoinColumn(name = "MEMBER_ID")
      }
  )
  private List<Address> addresseHistory = new ArrayList<>();


}
