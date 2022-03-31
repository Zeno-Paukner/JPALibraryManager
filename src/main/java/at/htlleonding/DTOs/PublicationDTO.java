package at.htlleonding.DTOs;

import at.htlleonding.persistence.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Create PublicationDTO
public class PublicationDTO {

    private String title;

    private Date publishYear;

    private boolean isTranslated;

    private Integer language_id;

    private Integer genre_id;

    private Integer mediatype_id;

    private Integer publisher_id;

    private final List<Integer> authors_id = new ArrayList<>();

    private final List<Integer> topics_id = new ArrayList<>();

    public PublicationDTO() {
    }

    public PublicationDTO(String title, Date publishYear, boolean isTranslated, Integer language_id, Integer genre_id, Integer mediatype_id, Integer publisher_id) {
        this.title = title;
        this.publishYear = publishYear;
        this.isTranslated = isTranslated;
        this.language_id = language_id;
        this.genre_id = genre_id;
        this.mediatype_id = mediatype_id;
        this.publisher_id = publisher_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Date publishYear) {
        this.publishYear = publishYear;
    }

    public boolean isTranslated() {
        return isTranslated;
    }

    public void setTranslated(boolean translated) {
        isTranslated = translated;
    }

    public Integer getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(Integer language_id) {
        this.language_id = language_id;
    }

    public Integer getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Integer genre_id) {
        this.genre_id = genre_id;
    }

    public Integer getMediatype_id() {
        return mediatype_id;
    }

    public void setMediatype_id(Integer mediatype_id) {
        this.mediatype_id = mediatype_id;
    }

    public Integer getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(Integer publisher_id) {
        this.publisher_id = publisher_id;
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
