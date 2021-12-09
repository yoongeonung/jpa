package jp.ac.hal.yoongeonung.jpa_practice1.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
