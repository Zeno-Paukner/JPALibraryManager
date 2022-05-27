package at.htlleonding.DTOs;

import at.htlleonding.persistence.Copy;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class CopyDTO implements Serializable {
    private final PublicationDTO publication;
    private final SaleDTO sale;
    private final RentDTO rent;
    private final Integer id;
    private final Date dateOfPurchase;
    private ClientDTO clientReservedBy;

    public CopyDTO(PublicationDTO publication, SaleDTO sale, Date dateOfPurchase) {
        this.publication = publication;
        this.sale = sale;
        this.rent = null;
        this.dateOfPurchase = dateOfPurchase;
        this.id = 0;
    }

    public CopyDTO(PublicationDTO publication, SaleDTO sale, Date dateOfPurchase, int id) {
        this.publication = publication;
        this.sale = sale;
        this.rent = null;
        this.dateOfPurchase = dateOfPurchase;
        this.id = id;
    }

    public CopyDTO(PublicationDTO publication, RentDTO rent, Date dateOfPurchase) {
        this.publication = publication;
        this.rent = rent;
        this.sale = null;
        this.dateOfPurchase = dateOfPurchase;
        this.id = 0;
    }

    public CopyDTO(PublicationDTO publication, RentDTO rent, Date dateOfPurchase, int id) {
        this.publication = publication;
        this.rent = rent;
        this.sale = null;
        this.dateOfPurchase = dateOfPurchase;
        this.id = id;
    }

    public PublicationDTO getPublication() {
        return publication;
    }

    public SaleDTO getSale() {
        return sale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CopyDTO entity = (CopyDTO) o;
        return Objects.equals(this.publication, entity.publication) &&
                Objects.equals(this.sale, entity.sale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publication, sale);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "publication = " + publication + ", " +
                "sale = " + sale + ")";
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public int getId() {
        return id;
    }

}
