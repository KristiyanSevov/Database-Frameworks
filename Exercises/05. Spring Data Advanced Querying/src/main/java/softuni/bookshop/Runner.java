package softuni.bookshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.bookshop.entities.Author;
import softuni.bookshop.entities.Book;
import softuni.bookshop.entities.Category;
import softuni.bookshop.enums.AgeRestriction;
import softuni.bookshop.enums.EditionType;
import softuni.bookshop.projections.ReducedBook;
import softuni.bookshop.services.AuthorService;
import softuni.bookshop.services.BookService;
import softuni.bookshop.services.CategoryService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        seedDatabase();
        //Ex.1
        //bookTitlesByAgeRestriction(reader.readLine());
        //Ex.2
        //goldenBooks();
        //Ex.3
        //booksByPrice();
        //Ex.4
        //notReleasedBooks(reader.readLine());
        //Ex.5
        //booksReleasedBefore(reader.readLine());
        //Ex.6
        //authorsWithNameEndingWith(reader.readLine());
        //Ex.7
        //booksWithTitleContaining(reader.readLine());
        //Ex.8
        //booksWithAuthorsWithNameStartingWith(reader.readLine());
        //Ex.9
        //countBooksWithTitleLongerThan(reader.readLine());
        //Ex.10 (Results differ from example because of randomness in the seed method)
        //totalBookCopiesByAuthor();
        //Ex.11
        //bookByTitleWithProjection(reader.readLine());
        //Ex.12
        //increaseBookCopies(reader.readLine(), reader.readLine());
        //Ex.13
        //removeBooksWithCopiesLessThan(reader.readLine());
        //Ex.14
        //countBooksByAuthorWithProcedure(reader.readLine());
    }

    private void countBooksByAuthorWithProcedure(String name) {
        String[] names = name.split("\\s+");
        String firstName = names[0];
        String lastName = names[1];
        int count = bookService.countBooksByAuthor(firstName, lastName);
        if (count == 0) {
            System.out.println(name + " has not written any books yet");
        } else {
            System.out.printf("%s has written %d books%n", name, count);
        }
    }

    private void removeBooksWithCopiesLessThan(String copies) {
        int numCopies = Integer.parseInt(copies);
        int removedBooks = bookService.removeBooksWithCopiesLessThan(numCopies);
        System.out.printf("%d books were deleted%n", removedBooks);
    }

    private void increaseBookCopies(String dateString, String numCopies) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        Date date = df.parse(dateString);
        int copies = Integer.parseInt(numCopies);
        int updatedBooks = bookService.increaseBookCopies(date, copies);
        System.out.println(updatedBooks * copies);
    }

    private void bookByTitleWithProjection(String title) {
        ReducedBook book = bookService.getBookByTitle(title);
        if (book == null) {
            System.out.println("No such book!");
        } else {
            System.out.printf("%s %s %s %.2f%n",
                    book.getTitle(), book.getEditionType(), book.getAgeRestriction(), book.getPrice());
        }
    }

    private void totalBookCopiesByAuthor() {
        bookService.getBookCopiesByAuthor().forEach(x -> System.out.println(x[0] + " - " + x[1]));
    }

    private void countBooksWithTitleLongerThan(String input) {
        int length = Integer.parseInt(input);
        System.out.println(bookService.getBookCountWithTitleLongerThan(length));
    }

    private void booksWithAuthorsWithNameStartingWith(String nameBeginning) {
        bookService.getBooksWithAuthorsNameStartingWith(nameBeginning)
                .forEach(x -> System.out.printf(
                        "%s (%s %s)%n", x.getTitle(), x.getAuthor().getFirstName(), x.getAuthor().getLastName()));
    }

    private void booksWithTitleContaining(String containedString) {
        bookService.getBooksContaining(containedString).forEach(x -> System.out.println(x.getTitle()));
    }

    private void authorsWithNameEndingWith(String nameEnding) {
        authorService.getAuthorsEndingWith(nameEnding)
                .forEach(x -> System.out.println(x.getFirstName() + " " + x.getLastName()));
    }

    private void booksReleasedBefore(String input) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(input);
        bookService.getBooksReleasedBefore(date)
                .forEach(x -> System.out.printf(
                        "Title: %s; Edition type: %s; Price: %.2f%n", x.getTitle(), x.getEditionType(), x.getPrice()));
    }

    private void notReleasedBooks(String input) throws ParseException {
        int year = Integer.parseInt(input);
        bookService.getNotReleasedBooks(year).forEach(x -> System.out.println(x.getTitle()));
    }

    private void booksByPrice() {
        bookService.getBooksByPrice().forEach(x -> System.out.printf("%s - %.2f%n", x.getTitle(), x.getPrice()));
    }

    private void goldenBooks() {
        bookService.getGoldenBooksLessThan5000Copies().forEach(x -> System.out.println(x.getTitle()));
    }

    private void bookTitlesByAgeRestriction(String input) {
        AgeRestriction ageRestriction = AgeRestriction.valueOf(input.toUpperCase());
        bookService.getBooksByAgeRestriction(ageRestriction).forEach(x -> System.out.println(x.getTitle()));
    }

    private void seedDatabase() throws IOException, ParseException {
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
        while ((line = booksReader.readLine()) != null) {
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