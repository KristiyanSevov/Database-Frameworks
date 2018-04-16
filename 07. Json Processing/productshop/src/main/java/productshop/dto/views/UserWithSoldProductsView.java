package productshop.dto.views;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class UserWithSoldProductsView implements Serializable {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
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
