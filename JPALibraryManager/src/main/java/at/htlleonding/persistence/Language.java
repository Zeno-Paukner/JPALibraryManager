package at.htlleonding.persistence;
import javax.persistence.*;

@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String language;

    public Integer getId() {
        return id;
    }

    public String getLanguage () {
        return language;
    }

    public void setLanguage(String language){this.language=language; }


}
