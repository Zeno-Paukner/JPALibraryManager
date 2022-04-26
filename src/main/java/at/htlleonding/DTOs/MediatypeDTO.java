package at.htlleonding.DTOs;

import at.htlleonding.persistence.MediatypeEnum;

import javax.persistence.Column;

public class MediatypeDTO {
    public MediatypeDTO(String mediaTypeDTO){
        this.mediatype=mediaTypeDTO;
    }

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

    public MediatypeDTO(String mediatype, Double price) {
        this.mediatype = mediatype;
        this.price = price;
    }

    public MediatypeDTO() {
    }
}
