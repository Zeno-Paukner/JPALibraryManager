package at.htlleonding.persistence;
import javax.persistence.*;
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


    public Copy(Sale sale, Rent rent, Date dateOfPurchase) {
        this.sale = sale;
        this.rent = rent;
        this.dateOfPurchase = dateOfPurchase;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Copy() {

    }

    public Integer getId() {
        return id;
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


}
