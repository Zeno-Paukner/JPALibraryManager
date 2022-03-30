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

    @Column
    private Integer copiesAmount;

    @Column
    private Double totalSalesPrice;

    @OneToMany(mappedBy = "invoice")
    private final List<Sale> sale = new ArrayList<>();

    public Invoice(Client client, Employee employee, Date saleDate, Integer amount, Double totalSalesPrices) {
        this.client = client;
        this.employee = employee;
        this.saleDate = saleDate;
        this.copiesAmount = amount;
        this.totalSalesPrice = totalSalesPrices;
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

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getCopiesAmount() {
        return copiesAmount;
    }

    public void setCopiesAmount(Integer copiesAmount) {
        this.copiesAmount = copiesAmount;
    }

    public Double getTotalSalesPrice() {
        return totalSalesPrice;
    }

    public void setTotalSalesPrice(Double totalSalesPrice) {
        this.totalSalesPrice = totalSalesPrice;
    }

    public List<Sale> getSale() {
        return sale;
    }
}