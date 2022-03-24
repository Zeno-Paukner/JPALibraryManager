package at.htlleonding.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    private List<Publication> publications = new ArrayList<>();

    public Topic(Integer id, ArrayList<Publication> publication) {
        this.id = id;
        this.publications = publication;
    }

    public Topic() {

    }

    public Integer getId() {
        return id;
    }

    public Topic(Integer id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(ArrayList<Publication> publication) {
        this.publications = publication;
    }
}
