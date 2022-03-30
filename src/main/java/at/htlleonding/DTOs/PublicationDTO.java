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

    private String language_id;

    private String genre_id;

    private String mediatype_id;

    private String publisher_id;

    private final List<Integer> authors_id = new ArrayList<>();

    private final List<Integer> topics_id = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public Date getPublishYear() {
        return publishYear;
    }

    public boolean isTranslated() {
        return isTranslated;
    }

    public String getLanguage_id() {
        return language_id;
    }

    public String getGenre_id() {
        return genre_id;
    }

    public String getMediatype_id() {
        return mediatype_id;
    }

    public String getPublisher_id() {
        return publisher_id;
    }

    public List<Integer> getAuthors_ids() {
        return authors_id;
    }

    public List<Integer> getTopics_ids() {
        return topics_id;
    }
}
