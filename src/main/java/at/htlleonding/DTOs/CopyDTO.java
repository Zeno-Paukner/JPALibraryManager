package at.htlleonding.DTOs;

import at.htlleonding.persistence.Publication;
import at.htlleonding.persistence.Rent;
import at.htlleonding.persistence.Sale;

import javax.persistence.*;
import java.util.Date;

public class CopyDTO {

    private Integer publication_id;

    public Integer getPublication_id() {
        return publication_id;
    }
}
