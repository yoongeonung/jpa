package valuetype;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Address {
    // 불변 객체, immutable object
    @Column(name = "WORK_CITY")
    private String city;
    @Column(name = "WORK_STREET")
    private String street;
    @Column(name = "WORK_ZIPCODE")
    private String zipcode;
}
