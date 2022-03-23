package at.htlleonding.persistence;


import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Author extends Person{

    @Id
    private Integer id;

    @ManyToMany
    private ArrayList<Publication> publications = new ArrayList<>();

}
