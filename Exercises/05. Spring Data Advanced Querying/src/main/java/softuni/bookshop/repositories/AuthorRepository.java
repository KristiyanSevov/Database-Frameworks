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

    List<Author> findAllByFirstNameEndingWith(String nameEnding);
}
