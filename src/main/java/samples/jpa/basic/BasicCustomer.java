package samples.jpa.basic;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data @Entity
public class BasicCustomer {

    public BasicCustomer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public BasicCustomer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String firstName;
    private String lastName;
}
