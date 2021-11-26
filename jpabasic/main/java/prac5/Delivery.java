package prac5;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "DELIVERY")
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ID")
    private Long id;
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY) // default EAGER -> LAZY로 변경
    private Order order;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STREET")
    private String street;
    @Column(name = "ZIPCODE")
    private String zipcode;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public Delivery(Order order, String city, String street, String zipcode, DeliveryStatus status) {
        this.order = order;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.status = status;
    }
}
