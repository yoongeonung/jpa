package jpabasic;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // JPAが管理するオブジェクト
@Table(name = "member")
@Getter @Setter
public class Member {
    @Id
    private Long id;
    private String name;
}
