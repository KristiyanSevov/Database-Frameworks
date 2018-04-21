package softuni.bookshop.services;

import softuni.bookshop.entities.Book;
import softuni.bookshop.enums.AgeRestriction;
import softuni.bookshop.projections.ReducedBook;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface BookService {

    void saveBook(Book book);

    List<Book> getBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> getGoldenBooksLessThan5000Copies();

    List<Book> getBooksByPrice();

    List<Book> getNotReleasedBooks(int year);

    List<Book> getBooksReleasedBefore(Date date);

    List<Book> getBooksContaining(String containedString);

    List<Book> getBooksWithAuthorsNameStartingWith(String nameBeginning);

    int getBookCountWithTitleLongerThan(int length);

    List<Object[]> getBookCopiesByAuthor();

    ReducedBook getBookByTitle(String title);

    int increaseBookCopies(Date date, int numCopies);

    int removeBooksWithCopiesLessThan(int numCopies);

    int countBooksByAuthor(String firstName, String lastName);
}
