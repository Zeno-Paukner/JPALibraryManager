package at.htlleonding;

import at.htlleonding.model.Person;

import io.quarkus.runtime.QuarkusApplication;

import javax.inject.Inject;
import java.util.List;

@io.quarkus.runtime.annotations.QuarkusMain
public class QuarkusCmdlineApp implements QuarkusApplication {
    @Inject
    LibraryService libraryService;

    @Override
    public int run(String... args) throws Exception {
        System.out.println("Hello World from Quarkus bloat...");

        libraryService.createTwins();

        List<Person> all = libraryService.selectAll();
        for (Person p : all) {
            System.out.println(p);
        }

        return 0;
    }
}
