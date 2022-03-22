package at.htlleonding.persistence;

import javax.persistence.*;
//Create a new Entity called Publisher with the following attributes:
//Primery Key: Id
//Column with date: PublisherName

@Entity
@Table(name = "Publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String publisherName;

    public Publisher() {
    }

    public Publisher(String publisherName) {
        this.publisherName = publisherName;
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


