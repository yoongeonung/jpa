package jp.ac.hal.yoongeonung.jpql;

import javax.persistence.*;
import java.util.List;

public class Jpqlmain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpql");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team team = new Team();
            team.setName("Kakao");

            Member member = new Member();
            member.setUsername("MemberA");
            member.setAge(20);
            // 연관관계 편의 메서드
            member.changeTeam(team);

            em.persist(member);
            em.persist(team);

            Query query = em.createQuery("select m from Member m inner join m.team t");
            List resultList = query.getResultList();

            // commit
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
