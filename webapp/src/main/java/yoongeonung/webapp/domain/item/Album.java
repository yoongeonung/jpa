package yoongeonung.webapp.domain.item;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Album extends Item {
  @Column(length = 50)
  private String artist;
  @Column(length = 50)
  private String etc;
}
