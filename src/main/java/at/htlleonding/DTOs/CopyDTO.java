package at.htlleonding.DTOs;

import java.io.Serializable;
import java.util.Objects;

public class CopyDTO implements Serializable {
    private final PublicationDTO publication;
    private final SaleDTO sale;
    private final int id;

    public CopyDTO(PublicationDTO publication, SaleDTO sale) {
        this.publication = publication;
        this.sale = sale;
        this.id = 0;
    }

    public CopyDTO(PublicationDTO publication, SaleDTO sale, int id) {
        this.publication = publication;
        this.sale = sale;
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

    public int getId() {
        return id;
    }
}
