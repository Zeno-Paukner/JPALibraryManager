package at.htlleonding.persistence;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String languageCode;

    @OneToMany(mappedBy = "language")
    private List<Publication> publications = new ArrayList<>();

    public Language(Integer id, String languageCode) {
        this.id = id;
        this.languageCode = languageCode;
    }

    public Language() {

    }

    public List<Publication> getPublications() {
        return publications;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
}
