package hellojpa;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable // 값 타입을 정의하는곳에 선언
public class Period {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
