package at.htlleonding.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer saleId;

    @OneToMany(mappedBy = "sale")
    private ArrayList<Copy> copyList = new ArrayList<Copy>();

    @ManyToOne
    @JoinColumn(name = "id")
    private Client client;

    @ManyToOne
    @JoinColumn (name = "id")
    private Employee employee;

    @Column
    private Date saleDate;

    @Column
    private Integer Amount;


    public Sale(Integer id, ArrayList<Copy> copyList, Date saleDate, Integer amount) {
        this.saleId = id;
        this.copyList = copyList;
        this.saleDate = saleDate;
        Amount = amount;
    }

    public Integer getId() {
        return saleId;
    }

    public void setId(Integer id) {
        this.saleId = id;
    }

    public ArrayList<Copy> getCopyList() {
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
