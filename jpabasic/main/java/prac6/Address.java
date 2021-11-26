package prac6;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable // 값타입
@Getter
@Setter(AccessLevel.PRIVATE) // 값타입이기 때문에 Immutable 하게 만들어준다.
@AllArgsConstructor // setter가 private하기 때문에 오직 생성자를 통해서만 값을 변경 가능(즉 새 객체를 생성해서 바꿔낄수있다.)
@NoArgsConstructor // JPA는 기본생성자를 통해 여러가지 작업을 하기때문에 꼭 필요하다.
public class Address {
    @Column(name = "CITY")
    private String city;
    @Column(name = "STREET")
    private String street;
    @Column(name = "ZIPCODE")
    private String zipcode;
}
