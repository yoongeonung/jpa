package jpabasic.valuetype;

import java.util.Optional;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class Address {
  private String city;
  private String street;
  private String zipcode;

  public Address(String city, String street, String zipcode) {
    this.city = city;
    this.street = street;
    this.zipcode = zipcode;
  }

}
