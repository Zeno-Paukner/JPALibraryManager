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

    private final List<Integer> authors_id = new ArrayList<>();

    private final List<Integer> topics_id = new ArrayList<>();

    public PublicationDTO() {
    }

    public PublicationDTO(String title, int publishYear, boolean isTranslated, Language language, Genre genre, Mediatype mediatype_id, Publisher publisher) {
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

    public Language getLanguage_id() {
        return language;
    }

    public void setLanguage_id(Language language_id) {
        this.language = language;
    }

    public Genre getGenre_id() {
        return genre;
    }

    public void setGenre_id(Genre genre) {
        this.genre = genre;
    }

    public Mediatype getMediatype_id() {
        return mediatype;
    }

    public void setMediatype_id(Mediatype mediatype_id) {
        this.mediatype = mediatype_id;
    }

    public Publisher getPublisher_id() {
        return publisher;
    }

    public void setPublisher_id(Publisher publisher_id) {
        this.publisher = publisher;
    }

    public List<Integer> setAuthors_id(List<Integer> authors) {
        for (Integer author : authors) {
            this.authors_id.add(author);
        }
        return this.authors_id;
    }

    public List<Integer> setTopics_id(List<Integer> topics) {
        for (Integer topic : topics) {
            this.topics_id.add(topic);
        }
        return this.topics_id;
    }

    public List<Integer> getAuthors_id() {
        return authors_id;
    }

    public List<Integer> getTopics_id() {
        return topics_id;
    }
}
