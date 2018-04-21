package productshop.dto.views;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class ProductsView implements Serializable{
    @Expose
    private Long count;
    @Expose
    private List<ProductView> products;

    public ProductsView() {
    }

    public ProductsView(Long count, List<ProductView> products) {
        this.count = count;
        this.products = products;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<ProductView> getProducts() {
        return products;
    }

    public void setProducts(List<ProductView> products) {
        this.products = products;
    }
}
