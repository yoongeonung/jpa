package jpabasic;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@Setter @Getter
public abstract class BaseEntity {
    private String createdB;
    private LocalDate createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDateTime;
}
