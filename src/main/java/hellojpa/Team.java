package hellojpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Team {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "TEAM_ID")
        private Long id;
        private String name;
}
