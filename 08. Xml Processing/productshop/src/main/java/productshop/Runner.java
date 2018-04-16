package productshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import productshop.dto.binding.*;
import productshop.dto.views.categories.CategoriesByCountView;
import productshop.dto.views.categories.CategoriesWrapperView;
import productshop.dto.views.productsInRange.ProductsInRangeView;
import productshop.dto.views.productsInRange.ProductsInRangeWrapper;
import productshop.dto.views.soldProducts.UserWithSoldProductsView;
import productshop.dto.views.usersAndProducts.UsersAndProductsView;
import productshop.dto.views.soldProducts.UsersWithSoldProductsWrapper;
import productshop.io.FileIO;
import productshop.parsers.XmlParser;
import productshop.services.api.CategoryService;
import productshop.services.api.ProductService;
import productshop.services.api.UserService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    private final FileIO fileIO;
    private final XmlParser parser;
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public Runner(FileIO fileIO,
                  XmlParser parser,
                  UserService userService,
                  ProductService productService,
                  CategoryService categoryService) {
        this.fileIO = fileIO;
        this.parser = parser;
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        importUsers();
        importProducts();
        importCategories();
        generateCategoriesForProducts();
        exportProductsInRange(500, 1000); //can also be from console, not specified in problem
        exportSuccessfullySoldProducts();
        exportCategoriesByProductCount();
        exportUsersAndProducts();
    }

    private void exportUsersAndProducts() throws IOException, JAXBException {
        UsersAndProductsView usersView = userService.getUsersAndProducts();
        String usersAndProductsXml = parser.write(usersView);
        fileIO.write(usersAndProductsXml, "/output/users-and-products.xml");
    }

    private void exportCategoriesByProductCount() throws IOException, JAXBException {
        List<CategoriesByCountView> categories = categoryService.findCategoriesWithProductCount();
        CategoriesWrapperView wrapper = new CategoriesWrapperView(categories);
        String categoriesXml = parser.write(wrapper);
        fileIO.write(categoriesXml, "/output/categories-by-products.xml");
    }

    private void exportSuccessfullySoldProducts() throws IOException, JAXBException {
        List<UserWithSoldProductsView> users = userService.findUsersWithSoldProducts();
        UsersWithSoldProductsWrapper wrapper = new UsersWithSoldProductsWrapper(users);
        String usersXml = parser.write(wrapper);
        fileIO.write(usersXml, "/output/users-sold-products.xml");
    }

    private void exportProductsInRange(int from, int to) throws IOException, JAXBException {
        List<ProductsInRangeView> products = productService.findProductsInRange(from, to);
        ProductsInRangeWrapper wrapper = new ProductsInRangeWrapper(products);
        String productsXml = parser.write(wrapper);
        fileIO.write(productsXml, "/output/products-in-range.xml");
    }

    private void generateCategoriesForProducts() {
        productService.generateCategories();
    }

    private void importCategories() throws IOException, JAXBException {
        String jsonUsers = fileIO.read("/input/categories.xml");
        CategoriesWrapper categoriesWrapper = parser.read(CategoriesWrapper.class, jsonUsers);
        categoryService.saveProducts(categoriesWrapper);
    }

    private void importProducts() throws IOException, JAXBException {
        String jsonUsers = fileIO.read("/input/products.xml");
        ProductsWrapper productWrapper = parser.read(ProductsWrapper.class, jsonUsers);
        productService.saveProducts(productWrapper);
    }

    private void importUsers() throws IOException, JAXBException {
        String jsonUsers = fileIO.read("/input/users.xml");
        UsersWrapper usersWrapper = parser.read(UsersWrapper.class, jsonUsers);
        userService.saveUsers(usersWrapper);
    }
}
