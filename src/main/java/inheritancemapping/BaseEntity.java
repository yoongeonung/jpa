package inheritancemapping;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
public abstract class BaseEntity {
    private String createdBy;
    private LocalDateTime createDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}
