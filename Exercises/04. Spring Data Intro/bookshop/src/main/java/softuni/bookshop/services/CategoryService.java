package softuni.bookshop.services;

import softuni.bookshop.entities.Category;

import java.util.List;

public interface CategoryService {

    void saveCategory(Category category);

    List<Category> getAllCategories();
}
