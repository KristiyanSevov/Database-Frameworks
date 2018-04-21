package productshop.dto.views.soldProducts;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithSoldProductsView implements Serializable {
    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name = "product")
    private List<ProductSoldView> products;

    public UserWithSoldProductsView() {
    }

    public UserWithSoldProductsView(String firstName, String lastName, List<ProductSoldView> products) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.products = products;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ProductSoldView> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSoldView> products) {
        this.products = products;
    }
}
