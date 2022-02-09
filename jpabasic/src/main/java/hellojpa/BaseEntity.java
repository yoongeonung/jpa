package hellojpa;

import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {
    private String createdB;
    private LocalDate createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDateTime;
}
