package at.htlleonding.DTOs;

import at.htlleonding.persistence.MediatypeEnum;

import javax.persistence.Column;

public class MediatypeDTO {

    private MediatypeEnum mediatypeEnum;

    private Double price;

    public MediatypeEnum getMediatypeEnum() {
        return mediatypeEnum;
    }

    public Double getPrice() {
        return price;
    }
}
