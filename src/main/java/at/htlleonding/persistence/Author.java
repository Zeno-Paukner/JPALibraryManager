package at.htlleonding.persistence;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author extends Person{

    @Id
    private Integer id;

    @ManyToMany
    private List<Publication> publications = new ArrayList<>();

    public Author() {

    }

    public List<Publication> getPublications() {
        return publications;
    }

    public Author(String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName, phoneNumber);
    }
}
