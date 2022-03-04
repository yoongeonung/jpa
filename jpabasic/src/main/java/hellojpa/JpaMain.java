package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * JPA에서 데이터를 변경하는 모든 작업은 트랜잭션 안에서 진행되어야 한다.
 */
public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Member2 member = new Member2();
            member.setName("Kakao");
            member.setHomeAddress(new Address("seoul", "garosu-gil", "1000"));

            member.getFavoriteFoods().add("Chicken");
            member.getFavoriteFoods().add("Pizza");

            member.getAddressHistory().add(new AddressEntity("inchoen", "inchoen-gil", "2000"));
            member.getAddressHistory().add(new AddressEntity("busan", "busan-gil", "3000"));
            member.getAddressHistory().add(new AddressEntity("ulsan", "ulsan-gil", "4000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println(" = = =  = = =  = = =  = = = ");
            Member2 findMember = em.find(Member2.class, 1L);
            System.out.println(" = = =  = = =  = = =  = = = ");
            //
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 엔티티매니저는 트랜잭션을 물고 작동하기 때문에 작업이 끝나면 종료하는게 중요!
        }
        emf.close();
    }
}
