package at.htlleonding.persistence;
import at.htlleonding.DTOs.CopyDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Copy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn (name= "publication_id")
    private Publication publication;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name= "sale_id")
    private Sale sale;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name= "rent_id")
    private Rent rent;

    @Column
    private Date dateOfPurchase;

    @Column
    Boolean isRented = false;

    public Copy(Publication publication, Rent rent, Date dateOfPurchase, Boolean isRented) {
        this.publication = publication;
        this.rent = rent;
        this.dateOfPurchase = dateOfPurchase;
        this.isRented = isRented;
    }

    public Copy(Publication publication, Sale sale, Date dateOfPurchase, Boolean isRented) {
        this.publication = publication;
        this.sale = sale;
        this.dateOfPurchase = dateOfPurchase;
        this.isRented = isRented;
    }

    public Copy() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Boolean getRented() {
        return isRented;
    }

    public void setRented(Boolean rented) {
        isRented = rented;
    }


}
