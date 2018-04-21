package softuni.bookshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.bookshop.entities.Author;
import softuni.bookshop.entities.Book;
import softuni.bookshop.repositories.AuthorRepository;
import softuni.bookshop.repositories.BookRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> getBooksAfterYear(Date date) {
        return bookRepository.findAllByReleaseDateAfter(date);
    }

    @Override
    public List<Book> getBooksByAuthor(String firstName, String lastName) {
        Author author = authorService.findByNames(firstName, lastName);
        return bookRepository.findAllByAuthorOrderByReleaseDateDescTitleAsc(author);
    }
}
