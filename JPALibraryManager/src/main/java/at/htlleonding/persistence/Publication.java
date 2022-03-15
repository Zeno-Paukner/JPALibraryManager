package at.htlleonding.persistence;

import org.hibernate.annotations.common.reflection.XProperty;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;

@Entity
@Table(name = "Publication")
public class Publication    {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer publication_id;

    @Column
    @Enumerated(EnumType.STRING)
    private Mediatype mediatype;

    @Column
    private String title;

    @Column
    private int publisherId;

    @OneToMany (mappedBy = "publication")
    private ArrayList<Copy> copyList = new ArrayList<Copy>();

    public Mediatype getMediatype() {
        return mediatype;
    }

    public void setMediatype(Mediatype mediatype) {
        this.mediatype = mediatype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }
}
