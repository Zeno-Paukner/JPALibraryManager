package at.htlleonding;

import at.htlleonding.DTOs.*;
import at.htlleonding.Logic.*;
import at.htlleonding.XMLDTOs.AuthorDTO;
import at.htlleonding.XMLDTOs.WorkPublicationDTO;
import at.htlleonding.repository.LibraryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import at.htlleonding.XMLDTOs.AuthorWorkDTO;
import at.htlleonding.XMLDTOs.WorksOfAuthorDTO;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static at.htlleonding.persistence.MediatypeEnum.BOOK;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class WorksOfAuthorsXmlTest {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu");

    @Inject
    EntityManager entityManager;
    @Inject
    LibraryRepository target;
    @Inject
    LibraryLogic libTarget;
    @Inject
    PublicationLogic pubTarget;
    @Inject
    GenreLogic genreTarget;
    @Inject
    TopicLogic topicTarget;
    @Inject
    RentLogic rentTarget;
    @Inject
    ClientLogic clientTarget;
    @Inject
    MediatypeLogic mediaTarget;
    @Inject
    LanguageLogic languageTarget;
    @Inject
    SaleLogic saleTarget;
    @Inject
    InvoiceLogic invoiceTarget;

    @Test
    public void createWorksOfAuthorDTO_serializeToXml_deserialize_checkObjects() {
        var worksOfAuthors = new WorksOfAuthorDTO();

        //Create an Author
        var author1 = new AuthorDTO("Romeo", "Bhuiyan", LocalDate.parse("15.02.2004", dtf), LocalDate.parse("12.06.2022", dtf));

        //First Book
        var romyImWunderland = new AuthorWorkDTO("Romy im Wunderland", "Fantasy");
        romyImWunderland.getPublications().add(new WorkPublicationDTO("BaumVerlag", LocalDate.parse("12.06.2022", dtf), "PaperBook"));

        //Second Book
        var romyTheGreat = new AuthorWorkDTO("Romy the great", "Fiction");
        romyTheGreat.getPublications().add(new WorkPublicationDTO("BaumVerlag", LocalDate.parse("12.06.2022", dtf), "PaperBook"));
        romyTheGreat.getPublications().add(new WorkPublicationDTO("AstVerlag", LocalDate.parse("11.06.2022", dtf), "E-Book"));

        author1.getWorks().add(romyImWunderland);
        author1.getWorks().add(romyTheGreat);

        worksOfAuthors.getAuthors().add(author1);

        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        StringBuilder sb = new StringBuilder();
        try {
            sb.append(xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(worksOfAuthors));
            var result1 = sb.toString();
            assertTrue(result1.contains("Romeo"));
            assertTrue(result1.contains("Romy im Wunderland"));
            System.out.println(result1);

            Reader reader = new StringReader(result1);
            var result2 = (WorksOfAuthorDTO)xmlMapper.readValue(reader, WorksOfAuthorDTO.class);
            assertEquals("Bhuiyan", result2.getAuthors().get(0).getLastName());
            assertEquals("Romy im Wunderland", result2.getAuthors().get(0).getWorks().get(0).getTitle());
        }
        catch (Exception e) {
            fail(e);
        }
    }

    public void generateData(){
        Date date = new Date(System.currentTimeMillis());
        var mediatypeBOOK = new MediatypeDTO(BOOK, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new at.htlleonding.DTOs.AuthorDTO("Romeo", "Bhuiyan");
        var author2 = new at.htlleonding.DTOs.AuthorDTO("Zeno", "Paukner");
        var author3 = new at.htlleonding.DTOs.AuthorDTO("Martin", "Hausleitner");

        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeBOOK, publisherIrgendwas);
        var pub2 = new PublicationDTO("Zeno im Wunderland",1901, true, languageEN, genreFantasy, mediatypeBOOK, publisherIrgendwas);
        var pub3 = new PublicationDTO("Martin im Wunderland",1902, true, languageEN, genreFantasy, mediatypeBOOK, publisherIrgendwas);

        pub1.getAuthors().add(author1);
        pub2.getAuthors().add(author2);
        pub3.getAuthors().add(author3);

        var copy1 = new CopyDTO(pub1, (RentDTO) null, date);
        var copy2 = new CopyDTO(pub2, (RentDTO) null, date);
        var copy3 = new CopyDTO(pub3, (RentDTO) null, date);

        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");
        //var rent1 = new RentDTO(copy1, employee1, client1);

        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeBOOK);
        languageTarget.createLanguage(languageEN);

        libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck1 = libTarget.createCopy(copy1);
        var copyCheck2 = libTarget.createCopy(copy2);
        var copyCheck3 = libTarget.createCopy(copy3);

        var aut1 =libTarget.createAuthor(author1);
        var aut2 = libTarget.createAuthor(author2);
        var aut3 = libTarget.createAuthor(author3);

        libTarget.createClient(client1);
        var publication1 = pubTarget.createPublication(pub1);
        publication1.getAuthors().add(aut1);

        var publication2 = pubTarget.createPublication(pub2);
        publication2.getAuthors().add(aut2);

        var publication3 = pubTarget.createPublication(pub3);
        publication3.getAuthors().add(aut3);
    }

    @Test
    @TestTransaction
    public void createWorks_serializeToXml_checkWithXsd() throws IOException {
        generateData();

        target.FlushAndClear();

        var publications = target.getAllPublications();
        Assertions.assertEquals(3, publications.size());
        var authors = target.getAllAuthors();
        Assertions.assertEquals(3, authors.size());

        var publication1 = publications.get(0);
        var publication2 = publications.get(1);
        var publication3 = publications.get(2);

        var aut1 = authors.get(0);
        var aut2 = authors.get(1);
        var aut3 = authors.get(2);

        var worksOfAuthors = new WorksOfAuthorDTO();
        //Create an Author
        var author1 = new AuthorDTO(aut1.getFirstName(), aut1.getLastName(), LocalDate.parse("15.02.2004", dtf), LocalDate.parse("12.06.2022", dtf));
        var author2 = new AuthorDTO(aut2.getFirstName(), aut2.getLastName(), LocalDate.parse("15.02.2004", dtf), LocalDate.parse("12.06.2022", dtf));
        var author3 = new AuthorDTO(aut3.getFirstName(), aut3.getLastName(), LocalDate.parse("15.02.2004", dtf), LocalDate.parse("12.06.2022", dtf));

        //First Book
        var book1 = new AuthorWorkDTO(publication1.getTitle(), publication1.getGenre().getGenre());
        book1.getPublications().add(new WorkPublicationDTO(publication1.getPublisher().getPublisherName(), LocalDate.parse("12.06.2022", dtf), publication1.getMediatype().getMediatypeEnum().toString()));

        var book2 = new AuthorWorkDTO(publication2.getTitle(), publication2.getGenre().getGenre());
        book2.getPublications().add(new WorkPublicationDTO(publication2.getPublisher().getPublisherName(), LocalDate.parse("12.06.2022", dtf), publication2.getMediatype().getMediatypeEnum().toString()));

        var book3 = new AuthorWorkDTO(publication3.getTitle(), publication3.getGenre().getGenre());
        book3.getPublications().add(new WorkPublicationDTO(publication3.getPublisher().getPublisherName(), LocalDate.parse("12.06.2022", dtf), publication3.getMediatype().getMediatypeEnum().toString()));

        author1.getWorks().add(book1);
        author2.getWorks().add(book2);
        author3.getWorks().add(book3);

        worksOfAuthors.getAuthors().add(author1);
        worksOfAuthors.getAuthors().add(author2);
        worksOfAuthors.getAuthors().add(author3);


        String xml = target.convertToXml(worksOfAuthors);


        String xsdFile = "xsd/report.xsd";
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = null;
        target.exportToXml("./output/out.xml", xml);

        try {
            schema = schemaFactory.newSchema(new Source[] {
                    new StreamSource(new File(xsdFile))
            });
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }

        StringReader stringReader = new StringReader(xml);
        try {
            schema.newValidator().validate(new StreamSource(stringReader));
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
        stringReader.close();
    }

}
