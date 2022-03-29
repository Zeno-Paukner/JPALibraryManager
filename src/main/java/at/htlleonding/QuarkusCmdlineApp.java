package at.htlleonding;

import at.htlleonding.repository.LibraryRepository;
import io.quarkus.runtime.QuarkusApplication;

import javax.inject.Inject;

@io.quarkus.runtime.annotations.QuarkusMain
public class QuarkusCmdlineApp implements QuarkusApplication {
    @Inject
    LibraryRepository libraryService;

    @Override
    public int run(String... args) throws Exception {
        System.out.println("Hello World from Quarkus bloat...");

        //libraryService.createTwins();

        //List<Publication> all = libraryService.selectAll();
        //for (Publication p : all) {
        //    System.out.println(p);
        //}

        return 0;
    }
}
