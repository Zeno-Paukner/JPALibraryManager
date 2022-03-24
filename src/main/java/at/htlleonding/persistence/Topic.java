package at.htlleonding.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String keyword;

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Publication> publications = new ArrayList<>();

    public Topic(String keyword) {
        this.keyword = keyword;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Publication> getPublications() {
        return publications;
    }
}
