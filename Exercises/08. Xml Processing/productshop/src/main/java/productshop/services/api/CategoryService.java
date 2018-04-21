package productshop.services.api;

import productshop.dto.binding.CategoriesWrapper;
import productshop.dto.views.categories.CategoriesByCountView;

import java.util.List;

public interface CategoryService {
    void saveProducts(CategoriesWrapper categoryModels);

    List<CategoriesByCountView> findCategoriesWithProductCount();
}
