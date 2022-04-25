package at.htlleonding.Logic;

import at.htlleonding.DTOs.MediatypeDTO;
import at.htlleonding.persistence.Mediatype;
import at.htlleonding.persistence.MediatypeEnum;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class MediatypeLogic {

    @Inject
    EntityManager entityManager;

    @Transactional
    //Mediatype
    public Integer createMediatype(MediatypeDTO mediatypeDTO) {
        Mediatype mediatype = new Mediatype();
        //compare String Mediatype with enum MediatypeEnum
        for (MediatypeEnum mediatypeEnum : MediatypeEnum.values()) {
            if (mediatypeDTO.getMediatype().equals(mediatypeEnum.toString())) {
                mediatype.setMediatypeEnum(mediatypeEnum);
            }
        }
        mediatype.setPrice(mediatypeDTO.getPrice());
        entityManager.persist(mediatype);
        return mediatype.getId();
    }

}
