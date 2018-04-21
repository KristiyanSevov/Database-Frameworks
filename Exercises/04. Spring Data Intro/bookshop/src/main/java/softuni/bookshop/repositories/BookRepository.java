package softuni.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.bookshop.entities.Author;
import softuni.bookshop.entities.Book;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(Date date);

    List<Book> findAllByAuthorOrderByReleaseDateDescTitleAsc(Author author);
}
