package at.htlleonding.persistence;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column
    private Date saleDate;

    public Double getTotalPriceAfterTax() {
        return TotalPriceAfterTax;
    }

    public void setTotalPriceAfterTax(Double totalPriceAfterTax) {
        TotalPriceAfterTax = totalPriceAfterTax;
    }

    private Integer Amount;
    private Integer Tax;
    private Double TotalPrice;
    private Double TotalPriceAfterTax;

    @OneToMany(mappedBy = "invoice")
    private final List<Sale> sale = new ArrayList<>();

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Invoice() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }



    public Integer getTax() {
        return Tax;
    }

    public void setTax(Integer tax) {
        Tax = tax;
    }

    public Double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        TotalPrice = totalPrice;
    }

    public List<Sale> getSale() {
        return sale;
    }
}