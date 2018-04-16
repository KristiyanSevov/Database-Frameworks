package productshop.dto.views;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class UserProductsView implements Serializable{
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Integer age;
    @Expose
    private ProductsView soldProducts;

    public UserProductsView() {
    }

    public UserProductsView(String firstName, String lastName, Integer age, ProductsView soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.soldProducts = soldProducts;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ProductsView getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(ProductsView soldProducts) {
        this.soldProducts = soldProducts;
    }
}
