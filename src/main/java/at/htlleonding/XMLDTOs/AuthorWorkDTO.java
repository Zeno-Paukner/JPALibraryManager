package at.htlleonding.XMLDTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

@JsonRootName("Work")
public class AuthorWorkDTO {
    @JacksonXmlProperty(isAttribute = true)
    String title;

    @JacksonXmlProperty(isAttribute = true)
    String genre;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("Publication")
    List<WorkPublicationDTO> publications = new ArrayList<WorkPublicationDTO>();

    public AuthorWorkDTO() {
    }

    public AuthorWorkDTO(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<WorkPublicationDTO> getPublications() {
        return publications;
    }

    public void setPublications(List<WorkPublicationDTO> publications) {
        this.publications = publications;
    }
}
