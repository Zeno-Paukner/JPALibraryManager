package at.htlleonding.Logic;

import at.htlleonding.DTOs.ClientDTO;
import at.htlleonding.DTOs.GenreDTO;
import at.htlleonding.persistence.Client;
import at.htlleonding.persistence.Genre;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;


@ApplicationScoped
public class ClientLogic {

    @Inject
    EntityManager entityManager;

    @Transactional
    public Client createClient(ClientDTO clientDTO) {
        Client client = new Client();
        for (Client client1 : entityManager.createQuery("SELECT c FROM Client c", Client.class).getResultList()) {
            if (client.getId().equals(clientDTO.getId())) {
                return client1;
            }
        }
        client.setFirstName(clientDTO.getFirstName()) ;
        client.setLastName(clientDTO.getLastName());
        client.setPhoneNumber(clientDTO.getPhoneNumber());
        client.setEmail(clientDTO.getEmail());
        entityManager.persist(client);
        return client;
    }
}
