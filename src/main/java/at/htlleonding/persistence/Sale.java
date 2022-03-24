package at.htlleonding.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "sale")
    private List<Copy> copyList = new ArrayList<Copy>();

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn (name = "employee_id")
    private Employee employee;

    @Column
    private Date saleDate;

    @Column
    private Integer Amount;

    @ManyToOne
    @JoinColumn (name = "invoice_id")
    private Invoice invoice;

    public Sale(Integer id, ArrayList<Copy> copyList, Date saleDate, Integer amount) {
        this.id = id;
        this.copyList = copyList;
        this.saleDate = saleDate;
        Amount = amount;
    }

    public Sale() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Copy> getCopyList() {
        return copyList;
    }

    public void setCopyList(ArrayList<Copy> copyList) {
        this.copyList = copyList;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }
}
