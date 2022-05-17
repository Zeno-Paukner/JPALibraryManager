package at.htlleonding;

import at.htlleonding.DTOs.*;
import at.htlleonding.Logic.*;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class LanguageLogicTest {

    @Inject
    LanguageLogic languageLogic;

    @TestTransaction
    @Test
    public void checkIfLanguageIsCreated() {
        //LanguageDTO languageDTO = new LanguageDTO();
        //var LanguageID = languageLogic.createLanguage(languageDTO);
        //Assertions.assertEquals(LanguageID, 1);


    }
}