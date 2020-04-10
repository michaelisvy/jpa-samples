package samples.jpa.locking;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
public class OLCustomerTest {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @BeforeEach
    public void init() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(new OLCustomer("John"));
        transaction.commit();
    }

    @Test
    public void shouldCreate2TransactionsJPA_API() {
        EntityManager entityManager1 = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction1 = entityManager1.getTransaction();
        transaction1.begin();
        Query query1 = entityManager1.createQuery("from OLCustomer where name= :name").setParameter("name", "John");
        OLCustomer customer1 = (OLCustomer) query1.getSingleResult();
        customer1.setName("John1");

        EntityManager entityManager2 = this.entityManagerFactory.createEntityManager();
        EntityTransaction transaction2 = entityManager2.getTransaction();
        transaction2.begin();
        Query query2 = entityManager2.createQuery("from OLCustomer where name= :name").setParameter("name", "John");
        OLCustomer customer2 = (OLCustomer) query2.getSingleResult();

        assertThat(customer2.getName()).isEqualTo("John");
        customer2.setName("John2");

        transaction1.commit();

        Exception exception = assertThrows(RollbackException.class, () -> {
            transaction2.commit();
        });

    }
}