package at.htlleonding.DTOs;

import at.htlleonding.persistence.MediatypeEnum;

import java.io.Serializable;
import java.util.Objects;

public class MediatypeDTO implements Serializable {
    private final MediatypeEnum mediatypeEnum;
    private final Double price;

    public MediatypeDTO(MediatypeEnum mediatypeEnum, Double price) {
        this.mediatypeEnum = mediatypeEnum;
        this.price = price;
    }

    public MediatypeEnum getMediatypeEnum() {
        return mediatypeEnum;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediatypeDTO entity = (MediatypeDTO) o;
        return Objects.equals(this.mediatypeEnum, entity.mediatypeEnum) &&
                Objects.equals(this.price, entity.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediatypeEnum, price);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "mediatypeEnum = " + mediatypeEnum + ", " +
                "price = " + price + ")";
    }

    public MediatypeEnum getMediatype() {
        return mediatypeEnum;
    }
}
