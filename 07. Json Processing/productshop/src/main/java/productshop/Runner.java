package productshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import productshop.dto.binding.CategoryCreateBindingModel;
import productshop.dto.binding.ProductCreateBindingModel;
import productshop.dto.binding.UserCreateBindingModel;
import productshop.dto.views.CategoriesByCountView;
import productshop.dto.views.ProductsInRangeView;
import productshop.dto.views.UserWithSoldProductsView;
import productshop.dto.views.UsersAndProductsView;
import productshop.io.FileIO;
import productshop.parsers.JsonParser;
import productshop.services.api.CategoryService;
import productshop.services.api.ProductService;
import productshop.services.api.UserService;

import java.io.IOException;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    private final FileIO fileIO;
    private final JsonParser parser;
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public Runner(FileIO fileIO,
                  JsonParser parser,
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

    private void exportUsersAndProducts() throws IOException {
        UsersAndProductsView usersView = userService.getUsersAndProducts();
        String usersAndProductsJson = parser.write(usersView);
        fileIO.write(usersAndProductsJson, "/output/users-and-products.json");
}

    private void exportCategoriesByProductCount() throws IOException {
        List<CategoriesByCountView> categories = categoryService.findCategoriesWithProductCount();
        String categoriesJson = parser.write(categories);
        fileIO.write(categoriesJson, "/output/categories-by-products.json");
    }

    private void exportSuccessfullySoldProducts() throws IOException {
        List<UserWithSoldProductsView> users = userService.findUsersWithSoldProducts();
        String usersJson = parser.write(users);
        fileIO.write(usersJson, "/output/users-sold-products.json");
    }

    private void exportProductsInRange(int from, int to) throws IOException {
        List<ProductsInRangeView> products = productService.findProductsInRange(from, to);
        String productsJson = parser.write(products);
        fileIO.write(productsJson, "/output/products-in-range.json");
    }

    private void generateCategoriesForProducts() {
        productService.generateCategories();
    }

    private void importCategories() throws IOException {
        String jsonUsers = fileIO.read("/input/categories.json");
        CategoryCreateBindingModel[] categoryModels = parser.read(CategoryCreateBindingModel[].class, jsonUsers);
        categoryService.saveProducts(categoryModels);
    }

    private void importProducts() throws IOException {
        String jsonUsers = fileIO.read("/input/products.json");
        ProductCreateBindingModel[] productModels = parser.read(ProductCreateBindingModel[].class, jsonUsers);
        productService.saveProducts(productModels);
    }

    private void importUsers() throws IOException {
        String jsonUsers = fileIO.read("/input/users.json");
        UserCreateBindingModel[] userModels = parser.read(UserCreateBindingModel[].class, jsonUsers);
        userService.saveUsers(userModels);
    }
}
