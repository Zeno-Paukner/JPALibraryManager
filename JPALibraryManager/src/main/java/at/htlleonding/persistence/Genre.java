package at.htlleonding.persistence;

import javax.persistence.*;

public class Genre {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Enumerated(EnumType.STRING)
    private String Genre;
}
