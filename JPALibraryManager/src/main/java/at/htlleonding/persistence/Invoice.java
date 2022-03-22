package at.htlleonding.persistence;
import javax.persistence.*;
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
    @JoinColumn(name = "saleId")
    private Sale sale;

    public Invoice() {
    }

    public Invoice(Sale sale) {
        this.sale = sale;
    }


    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}