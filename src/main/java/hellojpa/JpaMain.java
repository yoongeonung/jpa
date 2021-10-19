package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hello");
        EntityManager manager = factory.createEntityManager();
        // transaction
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        try {
            // 비영속 상태
            Member kakao = new Member("Kakao");
            kakao.setCreateDate(LocalDate.now());
            kakao.setDescription("loremloremloremloremloremloremloremloremloremloremloremloremloremlorem");
            kakao.setAge(20);
            kakao.setRoleType(RoleType.USER);
            // 영속화
            manager.persist(kakao);

            //commit
            System.out.println("<------------------");
            transaction.commit();
            System.out.println("------------------>");
        }catch (Exception e) {
            transaction.rollback();
        }finally {
            // close
            manager.close();
        }
        factory.close();
    }
}
