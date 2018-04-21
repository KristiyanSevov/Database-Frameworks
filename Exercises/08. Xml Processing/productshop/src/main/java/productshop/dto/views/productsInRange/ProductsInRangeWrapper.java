package productshop.dto.views.productsInRange;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsInRangeWrapper {
    @XmlElement(name = "product")
    private List<ProductsInRangeView> products;

    public ProductsInRangeWrapper() {
    }

    public ProductsInRangeWrapper(List<ProductsInRangeView> products) {
        this.products = products;
    }

    public List<ProductsInRangeView> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsInRangeView> products) {
        this.products = products;
    }
}
