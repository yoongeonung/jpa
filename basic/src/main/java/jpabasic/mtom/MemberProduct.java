package jpabasic.mtom;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBER_PRODUCT")
public class MemberProduct {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "MEMBER_PRODUCT_ID")
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "MEMBER_ID")
  private Member member;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "PRODUCT_ID")
  private Product product;

  @Column(name = "ORDERAMOUNT")
  private int orderAmount;

  @Column(name = "ORDERDATE")
  private LocalDateTime orderDate;
}
