package at.htlleonding.DTOs;

import java.io.Serializable;
import java.util.Objects;

public class TopicDTO implements Serializable {
    private final String keyword;

    public TopicDTO(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicDTO entity = (TopicDTO) o;
        return Objects.equals(this.keyword, entity.keyword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyword);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "keyword = " + keyword + ")";
    }
}
