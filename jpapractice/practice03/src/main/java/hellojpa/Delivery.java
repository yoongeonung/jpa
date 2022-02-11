package hellojpa;

import javax.persistence.*;

@Entity
@Table(name = "DELIVERY")
public class Delivery {
    @Id @Column(name = "DELIVERY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "delivery")
    private Order order;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STREET")
    private String street;
    @Column(name = "ZIPCODE")
    private String zipcode;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
