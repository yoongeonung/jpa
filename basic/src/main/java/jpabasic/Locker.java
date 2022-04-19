package jpabasic;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter @Getter
@Table(name = "locker")
public class Locker {
    @Id
    @Column(name = "locker_id")
    private Long id;
    private String name;
}
