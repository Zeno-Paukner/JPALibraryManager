package at.htlleonding.persistence;

import org.hibernate.annotations.common.reflection.XProperty;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Publication    {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @Column
    private String publishYear;

    @Column
    private boolean isTranslated;

    @ManyToOne
    @JoinColumn(name = "languageId")
    private Language language;

    @OneToMany(mappedBy = "genre")
    private ArrayList<Genre> genre = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "publisherId")
    private Publisher publisher;

    @OneToMany(mappedBy = "publication")
    private ArrayList<Copy> copies = new ArrayList<>();

    @ManyToMany
    private ArrayList<Author> authors = new ArrayList<>();

    @ManyToMany
    private ArrayList<Topic> topics = new ArrayList<>();
}
