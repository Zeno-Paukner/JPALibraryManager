package at.htlleonding.persistence;
import javax.persistence.*;

@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String language;

    public String getLanguage () {
        return language;
    }

    public void setLanguage(String language){this.language=language; }


}
