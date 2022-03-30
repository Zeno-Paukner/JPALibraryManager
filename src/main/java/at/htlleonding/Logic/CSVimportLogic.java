package at.htlleonding.Logic;
import at.htlleonding.DTOs.*;
import at.htlleonding.persistence.Author;
import at.htlleonding.persistence.Mediatype;
import at.htlleonding.persistence.Publication;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import at.htlleonding.Logic.LibraryLogic;


public class CSVimportLogic {

    //"title","publishYear","isTranslated","languageCode","Genre","Mediatype","Publisher Name","Authors","Topics"

    //String[] line = {"The Godfather","1972","true","en","Crime","0", "New York Times","Mario Puzo, Francis Ford Coppola","Crime, Drama"};

    // 0: PublicationDTO.setTitle 1: PublicationDTO.setPublishYear 2: PublicationDTO.setIsTranslated 3: LanguageDTO.setLanguageCode 4: GenreDTO.setGenre 5: MediatypeDTO.setMediaType 6: PublisherDTO.setPublisherName 7: TopicsDTO.setAuthors 8: PublisherDTO.setTopics

    //create importCSV for method with method insertLineToEnties
    public void importCSV(String fileName) {
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                insertLineToEnties(line.split(","));
                line = br.readLine();
            }

        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }



    public void insertLineToEnties(String[] line) throws ParseException {
        //---publication
        PublicationDTO publicationDTO = new PublicationDTO();
        publicationDTO.setTitle(line[0]);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        //CreatePublication with PublicationDTO
        //LibraryLogic.createPublication(publicationDTO);


        // Convert "1972" to Date and insert into setPublishYear
        publicationDTO.setPublishYear(sdf.parse(line[1]));
        publicationDTO.setTranslated(Boolean.parseBoolean(line[2]));


        //---language
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setLanguageCode(line[3]);

        //---genre
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setGenre(line[4]);

        //---mediatype
        MediatypeDTO mediatypeDTO = new MediatypeDTO();
        mediatypeDTO.setMediatype(line[5]);

        //---publisher
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setPublisherName(line[6]);


        //---Authors
        String[] authors = line[7].split(",");
        //Split authors into firstname and lastname
        for (String author : authors) {
            String[] authorSplit = author.split(" ");
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setFirstName(authorSplit[0]);
            authorDTO.setLastName(authorSplit[1]);
        }
        //publicationDTO.setAuthors_id(createAuthors(authors));

        //---Topics
        String[] topics = line[8].split(",");
        for (String topic : topics) {
            TopicDTO topicDTO = new TopicDTO();
            topicDTO.setKeyword(topic);
        }

}






}
