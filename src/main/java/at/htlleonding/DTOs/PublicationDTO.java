package at.htlleonding.DTOs;

import at.htlleonding.persistence.*;
import com.github.dockerjava.zerodep.shaded.org.apache.commons.codec.language.bm.Lang;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Create PublicationDTO
public class PublicationDTO {

    private String title;

    private int publishYear;

    private boolean isTranslated;

    private Language language;

    private Genre genre;

    private Mediatype mediatype;

    private Publisher publisher;

    private final List<Author> authors = new ArrayList<>();

    private final List<Topic> topics = new ArrayList<>();

    public PublicationDTO() {
    }

    public PublicationDTO(String title, int publishYear, boolean isTranslated, Language language, Genre genre, Mediatype mediatype, Publisher publisher) {
        this.title = title;
        this.publishYear = publishYear;
        this.isTranslated = isTranslated;
        this.language = language;
        this.genre = genre;
        this.mediatype= mediatype;
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

    public void setPublishYear(int publishYear) {this.publishYear = publishYear;}

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

    public void setMediatype(Mediatype mediatype) {this.mediatype = mediatype;}

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Author> setAuthors(List<Author> authors) {
        for (Author author : authors) {
            this.authors.add(author);
        }
        return this.authors;
    }

    public List<Topic> setTopics(List<Topic> topics) {
        for (Topic topic : topics) {
            this.topics.add(topic);
        }
        return this.topics;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Topic> getTopics() {
        return topics;
    }
}
