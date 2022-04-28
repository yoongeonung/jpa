package jpabasic.valuetype;

import java.time.LocalDateTime;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class Period {
  private LocalDateTime startDate;
  private LocalDateTime endDate;

  public Period(LocalDateTime startDate, LocalDateTime endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
