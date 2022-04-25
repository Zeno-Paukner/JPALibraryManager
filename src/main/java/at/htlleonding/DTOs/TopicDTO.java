package at.htlleonding.DTOs;

public class TopicDTO {
    private String keyword;

    public TopicDTO(String keyword) {
        this.keyword = keyword;
    }

    public TopicDTO() {
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
