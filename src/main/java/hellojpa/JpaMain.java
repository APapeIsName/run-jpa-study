package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Address address = new Address("city", "street", "100");

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setAddress(address);
            em.persist(member1);

            Address newAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());
            member1.setAddress(newAddress);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
