package valuetype;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
    @Column(name = "WORK_CITY")
    private String city;
    @Column(name = "WORK_STREET")
    private String street;
    @Column(name = "WORK_ZIPCODE")
    private String zipcode;
}
