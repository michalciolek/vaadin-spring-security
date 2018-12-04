package info.ciolek.starter.vaadin.spring.ui.secure.book;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>(Arrays.asList(
            new Book("Pan Tadeusz", "Adam Mickiewicz"),
            new Book("Potop", "Henryk Sienkiewicz")
    ));

    public List<Book> getBooks() {
        return books;
    }

    public String addBook() {
        return "TODO addBook()";
    }
}
