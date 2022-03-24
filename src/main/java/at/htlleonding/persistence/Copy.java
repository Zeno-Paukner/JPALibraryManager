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
    private Date buyDate;

    @Column
    private double price;

    public Copy(Integer id, Publication publication, Sale sale, Date buyDate, double price) {
        this.id = id;
        this.publication = publication;
        this.sale = sale;
        this.buyDate = buyDate;
        this.price = price;
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

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
