package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private final BookRepository bookRepository; // We will inject a book repository

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // This is the controller that takes the model as input and returns "books" to the view
    @RequestMapping("/books")
    public String getBooks(Model model){
        // Injects an instance of a BookRepository to get a list of books out of the database
        model.addAttribute("books", bookRepository.findAll());

        return "books/list"; // Return the "list" from the directory "books"
    }
}
