package at.htlleonding.persistence;

import javax.persistence.*;
import java.util.ArrayList;
//Create a new Entity called Publisher with the following attributes:
//Primery Key: Id
//Column with date: PublisherName

@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String publisherName;

    @OneToMany(mappedBy = "publisher")
    private ArrayList<Publication> publications = new ArrayList<>();

    public Publisher(Integer id, String publisherName) {
        this.id = id;
        this.publisherName = publisherName;
    }

    public Publisher() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}


