package at.htlleonding.XMLDTOs;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.time.LocalDate;

@JsonRootName("Publication")
public class WorkPublicationDTO {
    @JacksonXmlProperty(isAttribute = true)
    String publisher;

    @JacksonXmlProperty(isAttribute = true)
    LocalDate pubYear;

    @JacksonXmlProperty(isAttribute = true)
    String type;

    public WorkPublicationDTO(String publisher, LocalDate pubYear, String type) {
        this.publisher = publisher;
        this.pubYear = pubYear;
        this.type = type;
    }

    public WorkPublicationDTO() {
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPubYear() {
        return pubYear;
    }

    public void setPubYear(LocalDate pubYear) {
        this.pubYear = pubYear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
