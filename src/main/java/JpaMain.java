import proxy.Team;
import valuetype.Address;
import valuetype.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hello");
        EntityManager em = factory.createEntityManager();
        // transaction
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            // 회사 주소
            Address companyAddress = new Address("Seoul", "Gangnam", "110220");
            // 신입 1
            Member member1 = new Member();
            member1.setWorkAddress(companyAddress);
            // 신입 2
            Member member2 = new Member();
            // 값 복사를 통한 사용
            member2.setWorkAddress(new Address(companyAddress.getCity(), companyAddress.getStreet(), companyAddress.getZipcode()));
            // 신입 2가 전근으로 인해 주소를 변경
            member2.getWorkAddress().setCity("Busan");
            em.persist(member1);
            em.persist(member2);

            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
        }finally {
            // close
            em.close();
        }
        factory.close();
    }
}