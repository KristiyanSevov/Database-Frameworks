package productshop.services.api;

import productshop.dto.binding.ProductCreateBindingModel;
import productshop.dto.views.ProductsInRangeView;

import java.util.List;

public interface ProductService {
    void saveProducts(ProductCreateBindingModel[] productModels);

    void generateCategories();

    List<ProductsInRangeView> findProductsInRange(int from, int to);
}
