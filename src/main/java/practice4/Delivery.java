package practice4;

import javax.persistence.Entity;

@Entity
public class Delivery {
    private Long id;
    private Order order;
    private String city;
    private String street;
    private String zipcode;
    private DeliveryStatus status;
}
