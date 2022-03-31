package at.htlleonding.importExport;

import at.htlleonding.DTOs.*;
import at.htlleonding.Logic.*;
import at.htlleonding.persistence.Author;
import at.htlleonding.persistence.Topic;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class CSVimportLogic {

    //"title","publishYear","isTranslated","languageCode","Genre","Mediatype","Publisher Name","Authors","Topics"

    //String[] line = {"The Godfather","1972","true","en","Crime","0", "New York Times","Mario Puzo, Francis Ford Coppola","Crime, Drama"};

    // 0: PublicationDTO.setTitle 1: PublicationDTO.setPublishYear 2: PublicationDTO.setIsTranslated 3: LanguageDTO.setLanguageCode 4: GenreDTO.setGenre 5: MediatypeDTO.setMediaType 6: PublisherDTO.setPublisherName 7: TopicsDTO.setAuthors 8: PublisherDTO.setTopics

    //create importCSV for method with method insertLineToEnties
    public void importCSV(String fileName) {
        Path pathToFile = Paths.get(fileName);
        Boolean insertClient = false;
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            //if line hast more more then 3 elements
            if (line.split(",").length > 3) {
                insertClient = true;
            }
            while (line != null) {
                if (insertClient) {
                    insertClientLineToEnties(line.split(","));
                } else {
                    insertMediaLineToEnties(line.split(";"));
                }
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void insertMediaLineToEnties(String[] line) throws ParseException {
        //---publication
        PublicationLogic publicationLogic = new PublicationLogic();
        PublicationDTO publicationDTO = new PublicationDTO();
        publicationDTO.setTitle(line[0]);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        //CreatePublication with PublicationDTO



        // Convert "1972" to Date and insert into setPublishYear
        publicationDTO.setPublishYear(sdf.parse(line[1]));
        publicationDTO.setTranslated(Boolean.parseBoolean(line[2]));


        //---language
        LanguageLogic languageLogic = new LanguageLogic();
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setLanguageCode(line[3]);
        publicationDTO.setLanguage_id(languageLogic.createLanguage(languageDTO));

        //---genre
        GenreLogic genreLogic = new GenreLogic();
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setGenre(line[4]);
        publicationDTO.setGenre_id(genreLogic.createGenre(genreDTO));


        //---mediatype
        MediatypeLogic mediatypeLogic = new MediatypeLogic();
        MediatypeDTO mediatypeDTO = new MediatypeDTO();
        mediatypeDTO.setMediatype(line[5]);
        publicationDTO.setMediatype_id(mediatypeLogic.createMediatype(mediatypeDTO));


        //---publisher
        LibraryLogic libraryLogic = new LibraryLogic();
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setPublisherName(line[6]);
        publicationDTO.setPublisher_id(libraryLogic.createPublisher(publisherDTO));



        //---Authors
        Author author = new Author();
        List<Integer> allAuthors = new ArrayList<>();
        String[] authors = line[7].split(",");
        //Split authors into firstname and lastname
        for (String author2 : authors) {
            String[] authorSplit = author2.split(" ");
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setFirstName(authorSplit[0]);
            authorDTO.setLastName(authorSplit[1]);
            allAuthors.add(libraryLogic.createAuthor(authorDTO));
        }
        publicationDTO.setAuthors_id(allAuthors);
        //publicationDTO.setAuthors_id(createAuthors(authors));

        //---Topics
        TopicDTO topicDTO = new TopicDTO();
        TopicLogic topicLogic = new TopicLogic();
        String[] topics = line[8].split(",");
        List<Integer> allTopics = new ArrayList<>();
        for (String topic2 : topics) {
            topicDTO.setKeyword(topic2);
            allTopics.add(topicLogic.createTopic(topicDTO));
        }
        publicationDTO.setTopics_id(allTopics);
        publicationLogic.createPublication(publicationDTO);
    }

    // 0: ClientDTO.firstName 1: ClientDTO.lastName 2: ClientDTO.phoneNumber 3: ClientDTO.email
    public void insertClientLineToEnties(String[] line) throws ParseException {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName(line[0]);
        clientDTO.setLastName(line[1]);
        clientDTO.setPhoneNumber(line[2]);
        clientDTO.setEmail(line[3]);
    }

}




