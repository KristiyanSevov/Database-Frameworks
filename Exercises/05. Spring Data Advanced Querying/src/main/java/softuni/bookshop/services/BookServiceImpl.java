package softuni.bookshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.bookshop.entities.Book;
import softuni.bookshop.enums.AgeRestriction;
import softuni.bookshop.projections.ReducedBook;
import softuni.bookshop.repositories.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, EntityManager entityManager) {
        this.bookRepository = bookRepository;
        this.entityManager = entityManager;
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> getBooksByAgeRestriction(AgeRestriction ageRestriction) {
        return bookRepository.findAllByAgeRestriction(ageRestriction.toString());
    }

    @Override
    public List<Book> getGoldenBooksLessThan5000Copies() {
        return bookRepository.findAllByEditionTypeAndCopiesLessThan("GOLD", 5000);
    }

    @Override
    public List<Book> getBooksByPrice() {
        return bookRepository.findAllByPriceLessThanOrPriceGreaterThan(BigDecimal.valueOf(5), BigDecimal.valueOf(40));
    }

    @Override
    public List<Book> getNotReleasedBooks(int year) {
        return bookRepository.findBooksNotReleasedOn(year);
    }

    @Override
    public List<Book> getBooksReleasedBefore(Date date) {
        return bookRepository.findAllByReleaseDateBefore(date);
    }

    @Override
    public List<Book> getBooksContaining(String containedString) {
        return bookRepository.findAllByTitleContainingIgnoreCase(containedString);
    }

    @Override
    public List<Book> getBooksWithAuthorsNameStartingWith(String nameBeginning) {
        return bookRepository.findAllByAuthor_LastNameStartingWithIgnoreCase(nameBeginning);
    }

    @Override
    public int getBookCountWithTitleLongerThan(int length) {
        return bookRepository.findBooksWithTitleLengthMoreThan(length).size();
    }

    @Override
    public List<Object[]> getBookCopiesByAuthor() {
        return bookRepository.findCopiesByAuthor();
    }

    @Override
    public ReducedBook getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public int increaseBookCopies(Date date, int numCopies) {
        return bookRepository.updateBookCounts(date, numCopies);
    }

    @Override
    public int removeBooksWithCopiesLessThan(int numCopies) {
        return bookRepository.deleteAllByCopiesLessThan(numCopies);
    }

    @Override
    public int countBooksByAuthor(String firstName, String lastName) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("usp_get_bookcount");
        query.registerStoredProcedureParameter("first_name", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("last_name", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("result", Integer.class, ParameterMode.OUT);
        query.setParameter("first_name", firstName);
        query.setParameter("last_name", lastName);
        query.execute();
        return (int) query.getOutputParameterValue("result");
    }
}
