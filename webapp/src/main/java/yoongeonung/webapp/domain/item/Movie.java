package yoongeonung.webapp.domain.item;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Movie extends Item{
  @Column(length = 50)
  private String director;
  @Column(length = 50)
  private String actor;
}
