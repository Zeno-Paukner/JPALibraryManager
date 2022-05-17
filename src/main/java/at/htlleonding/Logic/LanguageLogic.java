package at.htlleonding.Logic;

import at.htlleonding.DTOs.LanguageDTO;
import at.htlleonding.persistence.Language;
import at.htlleonding.repository.LibraryRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class LanguageLogic {

    @Inject
    EntityManager entityManager;

    @Inject
    LibraryRepository ribrep;

    @Transactional
    public Language createLanguage(LanguageDTO languageDTO) {
        Language language = new Language();
        //check if String languageDTO.getLanguageCode() is already in the database at language.getLanguageCode()
        //not finished
        if (ribrep.getLanguageByLanguageCode(languageDTO.getLanguageCode()) != null) {
            if (languageDTO.getLanguageCode().length() != 2) {
                //check if the language is 2 letters long
                throw new RuntimeException("Language code must be 2 letters");
            }
            language.setLanguageCode(languageDTO.getLanguageCode());
            entityManager.persist(language);
        }
        return language;
    }


}
