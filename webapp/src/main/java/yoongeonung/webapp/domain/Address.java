package yoongeonung.webapp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Address {
  @Column(length = 50)
  private String city;
  @Column(length = 50)
  private String street;
  @Column(length = 50)
  private String zipcode;
}
