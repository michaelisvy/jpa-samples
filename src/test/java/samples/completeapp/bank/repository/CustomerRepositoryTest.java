package samples.completeapp.bank.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import samples.SamplesApplication;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SamplesApplication.class)
public class CustomerRepositoryTest {

    @Autowired
    private DataSource dataSource;


    @Test
    public void testDatabaseProvider() throws Exception {
        String databaseUrl = this.dataSource.getConnection().getMetaData().getURL();
        assertThat(databaseUrl).contains("h2");
    }

}