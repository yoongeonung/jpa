package jpabasic.mtom;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "PRODUCT")
public class Product {
™
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "PRODUCT_ID")
  private Long id;
  @Column(name = "NAME", length = 50)
  @Setter
  private String name;

}
