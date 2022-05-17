package at.htlleonding.persistence;

import org.hibernate.annotations.common.reflection.XProperty;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Publication    {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @Column
    private int publishYear;

    @Column
    private boolean isTranslated;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "mediatype_id")
    private Mediatype mediatype;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @OneToMany(mappedBy = "publication", cascade=CascadeType.ALL)
    private final List<Copy> copies = new ArrayList<>();

    @OneToMany(mappedBy = "publication", cascade=CascadeType.ALL)
    private final List<Reservation> reservations = new ArrayList<>();

    @ManyToMany(mappedBy = "publications")
    private final List<Author> authors = new ArrayList<>();

    @ManyToMany(mappedBy = "publications")
    private final List<Topic> topics = new ArrayList<>();

    public Publication(String title, int publishYear, boolean isTranslated) {
        this.title = title;
        this.publishYear = publishYear;
        this.isTranslated = isTranslated;
    }

    public Publication() {
    }

    public Publication(String title, int publishYear, boolean isTranslated, Language language, Genre genre, Mediatype mediatype, Publisher publisher) {
        this.title = title;
        this.publishYear = publishYear;
        this.isTranslated = isTranslated;
        this.language = language;
        this.genre = genre;
        this.mediatype = mediatype;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public Integer getId() {
        return id;
    }

    public void setPublishYear(int publishYear) {
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

    public Mediatype getMediatype() {
        return mediatype;
    }

    public void setMediatype(Mediatype mediatype) {
        this.mediatype = mediatype;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Copy> getCopies() {
        return copies;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public List<Author> getAuthors() {
        return authors;
    }

}
