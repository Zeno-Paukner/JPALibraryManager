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
    private Integer amount;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn (name = "invoice_id")
    private Invoice invoice;

    public Sale(Date saleDate, Integer amount) {
        this.saleDate = saleDate;
        this.amount = amount;
    }

    public Sale() {

    }


    public List<Copy> getCopyList() {
        return copyList;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {this.amount = amount;
    }

    public void setClient(Integer client_id) {
    }
}
