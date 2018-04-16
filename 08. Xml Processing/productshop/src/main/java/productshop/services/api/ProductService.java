package productshop.services.api;

import productshop.dto.binding.ProductsWrapper;
import productshop.dto.views.productsInRange.ProductsInRangeView;

import java.util.List;

public interface ProductService {
    void saveProducts(ProductsWrapper productModels);

    void generateCategories();

    List<ProductsInRangeView> findProductsInRange(int from, int to);
}
