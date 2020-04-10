package samples.jpa.locking;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
// used for tests on Optimistic Locking (therefore contains version attribute)
public class OLCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public OLCustomer(String name) {
        this.name = name;
    }

    private OLCustomer() {
    }

    @Version
    private int version;

}
