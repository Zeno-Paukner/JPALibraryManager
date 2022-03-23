package at.htlleonding.persistence;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//Create a new Entity called Invoice with the following attributes:
//Primery Key: Id
//mutiple Forain Key: SaleId


@Entity
@Table(name = "Invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "invoice")
    private List<Sale> sale = new ArrayList<>();

    public Invoice() {
    }

}