package at.htlleonding.persistence;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


//Create a new Entity called Mediatype with
//the following attributes:
//Primary Key: Id
//Foreign Key: PublicationId
//A Enmum Mediatype Column;
//A Price Column;

@Entity
public class Mediatype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private MediatypeEnum mediatypeEnum;

    @Column
    private Double price;

    @OneToMany(mappedBy = "mediatype", cascade=CascadeType.ALL)
    private final List<Publication> publication = new ArrayList<>();

    public Mediatype(MediatypeEnum mediatypeEnum, Double price) {
        this.mediatypeEnum = mediatypeEnum;
        this.price = price;
    }

    public Mediatype() {

    }

    public Integer getId() {
        return id;
    }

    public MediatypeEnum getMediatypeEnum() {
        return mediatypeEnum;
    }

    public void setMediatypeEnum(MediatypeEnum mediatypeEnum) {
        this.mediatypeEnum = mediatypeEnum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}



