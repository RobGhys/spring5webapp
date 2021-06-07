package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Component tells the spring framework that BootstrapData is a Spring-managed component.
// CommandLineRunner is an interface that has 1 method --> run
@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        /*
           Author & book & publisher
         */
        Publisher lesLiensQuiLiberent = new Publisher("Les Liens Qui Liberent", "14 Rue Des Champs", "Paris", "France", "6000");
        Author rob = new Author("Rob", "Ghyselinck");
        Book petitPrince = new Book("Le petit prince", "201903");
        publisherRepository.save(lesLiensQuiLiberent);

        rob.getBooks().add(petitPrince);
        petitPrince.getAuthors().add(rob);
        petitPrince.setPublisher(lesLiensQuiLiberent);
        lesLiensQuiLiberent.getBooks().add(petitPrince);

        // Saving both items to their repositories

        authorRepository.save(rob);
        bookRepository.save(petitPrince);
        publisherRepository.save(lesLiensQuiLiberent);

        Author caro  = new Author("Caroline", "Hazard");
        Book cuisine = new Book("Le gourmet", "29292929");

        caro.getBooks().add(cuisine);
        cuisine.getAuthors().add(caro);
        cuisine.setPublisher(lesLiensQuiLiberent);
        lesLiensQuiLiberent.getBooks().add(cuisine);

        // Saving again
        authorRepository.save(caro);
        bookRepository.save(cuisine);
        publisherRepository.save(lesLiensQuiLiberent);

        System.out.println("First program in Spring");
        System.out.println("There are: " + bookRepository.count() + " books, and " + authorRepository.count() + " authors.");
        System.out.println("And there are: " + publisherRepository.count() + " publishers");
        System.out.println("And publishers have published: " + lesLiensQuiLiberent.getBooks().size());
    }
}
