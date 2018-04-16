package productshop.services.api;

import productshop.dto.binding.CategoryCreateBindingModel;
import productshop.dto.views.CategoriesByCountView;

import java.util.List;

public interface CategoryService {
    void saveProducts(CategoryCreateBindingModel[] categoryModels);

    List<CategoriesByCountView> findCategoriesWithProductCount();
}
