package at.htlleonding.persistence;

import org.hibernate.annotations.common.reflection.XProperty;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;

@Entity
@Table(name = "Publication")
public class Publication    {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Enumerated(EnumType.STRING)
    private Mediatype mediatype;

    @Column
    private String title;

    @Column
    private int publisherId;

    @OneToMany (mappedBy = "publication")
    private ArrayList<Copy> copies = new ArrayList<>();

    @OneToMany
    private ArrayList<Author> authors = new ArrayList<>();

    @OneToMany
    private ArrayList<Topic> topics = new ArrayList<>();

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    public void setTopics(ArrayList<Topic> topics) {
        this.topics = topics;
    }

    public Integer getId() {
        return id;
    }

    public Mediatype getMediatype() {
        return mediatype;
    }

    public void setMediatype(Mediatype mediatype) {
        this.mediatype = mediatype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }
}
