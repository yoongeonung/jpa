package jpabasic;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import jpabasic.valuetype.Address;
import jpabasic.valuetype.Period;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;
  private String username;

  @Embedded
  private Period period;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(
          name = "city",
          column = @Column(name = "home_city")
      ),
      @AttributeOverride(
          name = "street",
          column = @Column(name = "home_street")
      ),
      @AttributeOverride(
          name = "zipcode",
          column = @Column(name = "home_zipcode")
      )
  })
  private Address homeAddress;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(
          name = "city",
          column = @Column(name = "work_city")
      ),
      @AttributeOverride(
          name = "street",
          column = @Column(name = "work_street")
      ),
      @AttributeOverride(
          name = "zipcode",
          column = @Column(name = "work_zipcode")
      )
  })
  private Address workAddress;

  public Member(String username, Period period, Address homeAddress,
      Address workAddress) {
    this.username = username;
    this.period = period;
    this.homeAddress = homeAddress;
    this.workAddress = workAddress;
  }

  public void changeWorkAddress(Address address) {
    this.workAddress = address;
  }
}
