package softuni.bookshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.bookshop.entities.Author;
import softuni.bookshop.repositories.AuthorRepository;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Set<Author> getAuthorsWithBookBefore(Date date) {
        return authorRepository.findByBooks_ReleaseDateBefore(date);
    }

    @Override
    public List<Author> getAuthorsOrderedByBookNumber() {
        return authorRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(x -> -x.getBooks().size()))
                .collect(Collectors.toList());
    }

    @Override
    public Author findByNames(String firstName, String lastName) {
        return authorRepository.findByFirstNameAndLastName(firstName, lastName);
    }

}
