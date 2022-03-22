package at.htlleonding.persistence;

import javax.persistence.*;

public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column
    private String genre;


    public Integer getId() {
        return Id;
    }

    public String getGenre() {
        return genre;
    }
}
