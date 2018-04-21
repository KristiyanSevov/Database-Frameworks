package softuni.bookshop.services;

import softuni.bookshop.entities.Author;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface AuthorService {

    void saveAuthor(Author author);

    List<Author> getAllAuthors();

    Set<Author> getAuthorsWithBookBefore(Date date);

    List<Author> getAuthorsOrderedByBookNumber();

    Author findByNames(String firstName, String lastName);
}
