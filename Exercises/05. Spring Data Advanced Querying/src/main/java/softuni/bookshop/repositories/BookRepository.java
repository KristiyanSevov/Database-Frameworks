package softuni.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.bookshop.entities.Author;
import softuni.bookshop.entities.Book;
import softuni.bookshop.projections.ReducedBook;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAgeRestriction(String ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(String edition, Integer copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lessThan, BigDecimal greaterThan);

    @Query(value = "SELECT b FROM Book as b WHERE FUNCTION('YEAR', b.releaseDate) <> :year")
    List<Book> findBooksNotReleasedOn(@Param(value = "year") int year);

    List<Book> findAllByReleaseDateBefore(Date date);

    List<Book> findAllByTitleContainingIgnoreCase(String containedString);

    List<Book> findAllByAuthor_LastNameStartingWithIgnoreCase(String nameBeginning);

    @Query(value = "SELECT b FROM Book as b WHERE FUNCTION('CHAR_LENGTH', b.title) > :length")
    List<Book> findBooksWithTitleLengthMoreThan(@Param(value = "length") long length);

    @Query(value = "SELECT FUNCTION('CONCAT', a.firstName, ' ', a.lastName), SUM(b.copies) FROM Book as b " +
            "JOIN b.author as a GROUP BY a.id ORDER BY SUM(b.copies) DESC ")
    List<Object[]> findCopiesByAuthor(); //not good practice but wanted to see if it works

    ReducedBook findByTitle(String title);

    @Modifying
    @Query(value = "UPDATE Book as b SET b.copies = b.copies + :numCopies WHERE b.releaseDate > :date")
    int updateBookCounts(@Param(value = "date") Date date, @Param(value = "numCopies") int count);

    int deleteAllByCopiesLessThan(int copies);

}
