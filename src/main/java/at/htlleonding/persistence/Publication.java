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

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "mediatype_id")
    private Mediatype mediatype;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @OneToMany(mappedBy = "publication", cascade=CascadeType.ALL)
    private List<Copy> copies = new ArrayList<>();

    @OneToMany(mappedBy = "publication", cascade=CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Author> authors = new ArrayList<>();

    @ManyToMany(cascade=CascadeType.ALL)
    private List<Topic> topics = new ArrayList<>();

    public Publication(String title, String publishYear, boolean isTranslated) {
        this.title = title;
        this.publishYear = publishYear;
        this.isTranslated = isTranslated;
    }

    public Publication() {

    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public boolean isTranslated() {
        return isTranslated;
    }

    public void setTranslated(boolean translated) {
        isTranslated = translated;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Mediatype getMediatype() {
        return mediatype;
    }

    public void setMediatype(Mediatype mediatype) {
        this.mediatype = mediatype;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public List<Copy> getCopies() {
        return copies;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

}
