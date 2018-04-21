package softuni.bookshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.bookshop.entities.Author;
import softuni.bookshop.entities.Book;
import softuni.bookshop.entities.Category;
import softuni.bookshop.enums.AgeRestriction;
import softuni.bookshop.enums.EditionType;
import softuni.bookshop.services.AuthorService;
import softuni.bookshop.services.BookService;
import softuni.bookshop.services.CategoryService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Component
public class Runner implements CommandLineRunner {

    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;

    @Autowired
    public Runner(AuthorService authorService,
                  BookService bookService,
                  CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        SeedDatabase();
        //Ex.1
        //GetBooksAfterYear();
        //Ex.2
        //GetAuthorsWithBookReleasedAfter();
        //Ex.3
        //GetAuthorsOrderedByBookNumber();
        //Ex.4
        //AllBooksByAuthor();
    }

    private void AllBooksByAuthor() {
        bookService.getBooksByAuthor("George", "Powell")
                .forEach(x -> System.out.printf("%s %s %s%n", x.getTitle(), x.getReleaseDate(), x.getCopies()));
    }

    private void GetAuthorsOrderedByBookNumber() {
        authorService.getAuthorsOrderedByBookNumber()
                .forEach(x -> System.out.printf("%s %s %s%n", x.getFirstName(), x.getLastName(), x.getBooks().size()));
    }

    private void GetAuthorsWithBookReleasedAfter() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse("1991-01-01");
        authorService.getAuthorsWithBookBefore(date)
                .forEach(x -> System.out.printf("%s %s%n", x.getFirstName(), x.getLastName()));
    }

    private void GetBooksAfterYear() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse("2001-01-01");
        bookService.getBooksAfterYear(date).forEach(x -> System.out.println(x.getTitle()));
    }

    private void SeedDatabase() throws IOException, ParseException {
        List<String> allAuthors = Files.readAllLines(Paths.get(System.getProperty("user.dir") +
                "/src/main/resources/" + "authors.txt"));
        for (String line : allAuthors) {
            String[] tokens = line.split("\\s+");
            String firstName = tokens[0];
            String lastName = tokens[1];
            Author author = new Author(firstName, lastName);
            authorService.saveAuthor(author);
        }
        List<String> allCategories = Files.readAllLines(Paths.get(System.getProperty("user.dir") +
                "/src/main/resources/" + "categories.txt"));
        for (String line : allCategories) {
            if (line.isEmpty()) {
                continue;
            }
            Category category = new Category(line);
            categoryService.saveCategory(category);
        }

        BufferedReader booksReader = new BufferedReader(new FileReader(System.getProperty("user.dir") +
                "/src/main/resources/" + "books.txt"));
        Random random = new Random();
        String line = "";
        List<Author> authors = authorService.getAllAuthors();
        List<Category> categories = categoryService.getAllCategories();
        while((line = booksReader.readLine()) != null){
            String[] data = line.split("\\s+");
            int authorIndex = random.nextInt(authors.size());
            Author author = authors.get(authorIndex);
            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(data[1]);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            titleBuilder.deleteCharAt(titleBuilder.length() - 1);
            String title = titleBuilder.toString();

            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setEditionType(editionType.toString());
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction.toString());

            HashSet<Category> bookCategories = new HashSet<>();
            bookCategories.add(categories.get(random.nextInt(categories.size())));
            bookCategories.add(categories.get(random.nextInt(categories.size())));
            book.setCategories(bookCategories);

            bookService.saveBook(book);
        }
    }
}