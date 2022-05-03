package at.htlleonding.DTOs;

import java.io.Serializable;
import java.util.Objects;

public class PublisherDTO implements Serializable {
    private final String publisherName;

    public PublisherDTO(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublisherDTO entity = (PublisherDTO) o;
        return Objects.equals(this.publisherName, entity.publisherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publisherName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "publisherName = " + publisherName + ")";
    }
}
