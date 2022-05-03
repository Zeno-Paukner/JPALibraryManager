package at.htlleonding.DTOs;

import java.io.Serializable;
import java.util.Objects;

public class LanguageDTO implements Serializable {
    private final String languageCode;

    public LanguageDTO(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LanguageDTO entity = (LanguageDTO) o;
        return Objects.equals(this.languageCode, entity.languageCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageCode);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "languageCode = " + languageCode + ")";
    }
}
