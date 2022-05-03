package at.htlleonding;

import at.htlleonding.Logic.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class MediaTypeLogicTest {

    @Inject
    TopicLogic topicLogic;
    GenreLogic genreLogic;
    SaleLogic saleLogic;
    LibraryLogic libraryLogic;
    EntityManager em;
    //@TestTransaction
    //@Test
    //public void CheckIfTheWritingIsRight() {
    //    MediatypeDTO mediatypeDTO= new MediatypeDTO("Book");
    //    var mediaTypeid = MediatypeLogic.createMediatype(mediatypeDTO);
    //    Assertions.assertEquals();
    //}
}
