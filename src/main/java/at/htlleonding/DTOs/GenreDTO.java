package at.htlleonding.DTOs;

public class GenreDTO {
    private String genre;

    public GenreDTO(String genre) {
        this.genre = genre;
    }
    public GenreDTO() {

    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
