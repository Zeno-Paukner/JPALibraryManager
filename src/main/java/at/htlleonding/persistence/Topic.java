package at.htlleonding.persistence;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Topic {
    @Id
    private Integer id;

    @ManyToMany
    private ArrayList<Publication> publication = new ArrayList<>();

    public Topic(Integer id, ArrayList<Publication> publication) {
        this.id = id;
        this.publication = publication;
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
}
