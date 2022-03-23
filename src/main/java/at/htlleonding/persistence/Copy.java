package at.htlleonding.persistence;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Copy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @ManyToOne
    @JoinColumn (name= "publicationId")
    private Publication publication;

    @ManyToOne
    @JoinColumn(name= "saleId")
    private Sale sale;

    @Column
    private Date buyDate;

    @Column
    private double price;

    public Copy(Integer id, Publication publication, Sale sale, Date buyDate, double price) {
        Id = id;
        this.publication = publication;
        this.sale = sale;
        this.buyDate = buyDate;
        this.price = price;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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
