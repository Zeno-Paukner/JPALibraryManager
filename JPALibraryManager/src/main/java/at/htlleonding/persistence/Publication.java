package at.htlleonding.persistence;

import org.hibernate.annotations.common.reflection.XProperty;

import javax.persistence.*;
import java.awt.*;

@Entity
@Table(name = "Publication")
public class Publication    {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String Title;

    private MediaType mediaType;

    private int PublisherId;

    private
}