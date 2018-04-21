package softuni.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.bookshop.entities.Author;
import softuni.bookshop.entities.Book;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Set<Author> findByBooks_ReleaseDateBefore(Date releaseDate);

    //the other way
    /*@Query(value = "SELECT a FROM Author as a JOIN a.books as b WHERE b.releaseDate < :year")
    Set<Author> getAuthorsWithBookBefore(@Param(value = "year") Date date);*/

    Author findByFirstNameAndLastName(String firstName, String lastName);

}
