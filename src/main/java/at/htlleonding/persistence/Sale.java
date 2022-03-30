package at.htlleonding.persistence;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "sale", cascade=CascadeType.ALL)
    private List<Copy> copyList = new ArrayList<Copy>();

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn (name = "employee_id")
    private Employee employee;

    @Column
    private Date saleDate;

    @Column




    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn (name = "invoice_id")
    private Invoice invoice;

    public Sale() {
    }

    public Sale(List<Copy> copyList, Client client, Employee employee, Date saleDate,  Invoice invoice) {
        this.copyList = copyList;
        this.client = client;
        this.employee = employee;
        this.saleDate = saleDate;

        this.invoice = invoice;
    }

    public List<Copy> getCopyList() {
        return copyList;
    }

    public void setCopyList(List<Copy> copyList) {
        this.copyList = copyList;
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


    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
