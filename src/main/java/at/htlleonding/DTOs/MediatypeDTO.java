package at.htlleonding.DTOs;

import at.htlleonding.persistence.MediatypeEnum;

import javax.persistence.Column;

public class MediatypeDTO {

    private String mediatype;

    private Double price;

    public String getMediatype() {
        return mediatype;
    }

    public void setMediatype(String mediatype) {
        this.mediatype = mediatype;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
