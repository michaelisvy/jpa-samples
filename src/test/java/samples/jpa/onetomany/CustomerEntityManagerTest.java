package samples.jpa.onetomany;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CustomerEntityManagerTest {

    public static final String LAST_NAME = "Smith";
    public static final String FIRST_NAME = "John";


    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    public void saveCustomer() {
        OtmCustomer customer = new OtmCustomer(FIRST_NAME, LAST_NAME);
        customer.addAccount(new OtmAccount((10)));
        customer.addAddress(new OtmAddress("3, Serangoon avenue 12", "558136"));
        this.entityManager.persist(customer);
        this.entityManager.flush(); // necessary otherwise foreign key is not updated
        assertThat(customer.getId()).isGreaterThan(0);

        this.entityManager.clear();
        OtmCustomer newCustomer = (OtmCustomer) this.entityManager.createQuery("from OtmCustomer where firstName=:firstName")
                .setParameter("firstName", FIRST_NAME).getSingleResult();
        assertThat(newCustomer.getAccounts().size()).isGreaterThan(0);
    }

}
