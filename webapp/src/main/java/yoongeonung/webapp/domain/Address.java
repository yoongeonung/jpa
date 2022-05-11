package yoongeonung.webapp.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
  @Column(length = 50)
  private String city;
  @Column(length = 50)
  private String string;
  @Column(length = 50)
  private String zipcode;
}
