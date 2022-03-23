package at.htlleonding.persistence;
import javax.persistence.*;


//Create a new Entity called Mediatype with
//the following attributes:
//Primary Key: Id
//Foreign Key: PublicationId
//A Enmum Mediatype Column;
//A Price Column;

@Entity
public class Mediatype {


    @Enumerated(EnumType.STRING)
    private MediatypeEnum mediatypeEnum;

    @Column
    private Double price;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;

    public Mediatype(Integer id, MediatypeEnum mediatypeEnum, Double price, Publication publication) {
        this.mediatypeEnum = mediatypeEnum;
        this.price = price;
        this.publication = publication;
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

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }
}



