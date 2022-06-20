package at.htlleonding.XMLDTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonRootName("Author")
public class AuthorDTO {
    @JacksonXmlProperty(isAttribute = true)
    String firstName;
    @JacksonXmlProperty(isAttribute = true)
    String lastName;
    @JacksonXmlProperty(isAttribute = true, localName = "dob")
    LocalDate dateBirth;
    @JacksonXmlProperty(isAttribute = true, localName = "dod")
    LocalDate dateDeath;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("Work")
    List<AuthorWorkDTO> works = new ArrayList<AuthorWorkDTO>();

    public AuthorDTO() {
    }

    public AuthorDTO(String firstName, String lastName, LocalDate dateBirth, LocalDate dateDeath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
        this.dateDeath = dateDeath;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public LocalDate getDateDeath() {
        return dateDeath;
    }

    public void setDateDeath(LocalDate dateDeath) {
        this.dateDeath = dateDeath;
    }


    public List<AuthorWorkDTO> getWorks() {
        return works;
    }

    public void setWorks(List<AuthorWorkDTO> works) {
        this.works = works;
    }
}
