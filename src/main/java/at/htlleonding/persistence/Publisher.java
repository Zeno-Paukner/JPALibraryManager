package at.htlleonding.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(mappedBy = "publisher", cascade=CascadeType.ALL)
    private final List<Publication> publications = new ArrayList<>();

    public Publisher(Integer id, String publisherName) {
        this.id = id;
        this.publisherName = publisherName;
    }

    public Publisher(String publisherName) {
        this.publisherName = publisherName;
    }

    public Publisher(Integer id) {
        this.id = id;
    }

    public Publisher() {

    }

    public List<Publication> getPublications() {
        return publications;
    }

    public Integer getId() {
        return id;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}


