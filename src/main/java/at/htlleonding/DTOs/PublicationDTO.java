package at.htlleonding.DTOs;

import java.io.Serializable;
import java.util.Objects;

public class PublicationDTO implements Serializable {
    private final String title;
    private final int publishYear;
    private final boolean isTranslated;
    private final LanguageDTO language;
    private final GenreDTO genre;
    private final MediatypeDTO mediatype;
    private final PublisherDTO publisher;

    public PublicationDTO(String title, int publishYear, boolean isTranslated, LanguageDTO language, GenreDTO genre, MediatypeDTO mediatype, PublisherDTO publisher) {
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

    public int getPublishYear() {
        return publishYear;
    }

    public boolean getIsTranslated() {
        return isTranslated;
    }

    public LanguageDTO getLanguage() {
        return language;
    }

    public GenreDTO getGenre() {
        return genre;
    }

    public MediatypeDTO getMediatype() {
        return mediatype;
    }

    public PublisherDTO getPublisher() {
        return publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationDTO entity = (PublicationDTO) o;
        return Objects.equals(this.title, entity.title) &&
                Objects.equals(this.publishYear, entity.publishYear) &&
                Objects.equals(this.isTranslated, entity.isTranslated) &&
                Objects.equals(this.language, entity.language) &&
                Objects.equals(this.genre, entity.genre) &&
                Objects.equals(this.mediatype, entity.mediatype) &&
                Objects.equals(this.publisher, entity.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, publishYear, isTranslated, language, genre, mediatype, publisher);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "title = " + title + ", " +
                "publishYear = " + publishYear + ", " +
                "isTranslated = " + isTranslated + ", " +
                "language = " + language + ", " +
                "genre = " + genre + ", " +
                "mediatype = " + mediatype + ", " +
                "publisher = " + publisher + ")";
    }
}
