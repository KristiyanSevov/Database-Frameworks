package productshop.dto.views.usersAndProducts;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsView {
    @XmlAttribute
    private Long count;
    @XmlElement(name = "product")
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
