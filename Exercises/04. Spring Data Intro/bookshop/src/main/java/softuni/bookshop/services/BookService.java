package softuni.bookshop.services;

import softuni.bookshop.entities.Book;

import java.util.Date;
import java.util.List;

public interface BookService {

    void saveBook(Book book);

    List<Book> getBooksAfterYear(Date date);

    List<Book> getBooksByAuthor(String firstName, String lastName);
}
