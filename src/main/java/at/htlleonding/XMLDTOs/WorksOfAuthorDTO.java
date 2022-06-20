package at.htlleonding.XMLDTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

@JsonRootName("WorksOfAuthors")
public class WorksOfAuthorDTO {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("Author")
    List<at.htlleonding.XMLDTOs.AuthorDTO> authors = new ArrayList<at.htlleonding.XMLDTOs.AuthorDTO>();

    public WorksOfAuthorDTO() {
    }

    public List<at.htlleonding.XMLDTOs.AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthor(List<at.htlleonding.XMLDTOs.AuthorDTO> authors) {
        this.authors = authors;
    }
}
