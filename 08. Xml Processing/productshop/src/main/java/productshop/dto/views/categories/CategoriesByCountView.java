package productshop.dto.views.categories;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesByCountView {
    @XmlAttribute(name = "name")
    private String category;
    @XmlElement(name = "products-count")
    private Integer productsCount;
    @XmlElement(name = "average-price")
    private Double averagePrice;
    @XmlElement(name = "total-revenue")
    private Double totalRevenue;

    public CategoriesByCountView() {
    }

    public CategoriesByCountView(String name, Integer productsCount, Double averagePrice, Double totalRevenue) {
        this.category = name;
        this.productsCount = productsCount;
        this.averagePrice = (double)Math.round(averagePrice * 1000000)/1000000;;
        this.totalRevenue = (double)Math.round(totalRevenue * 100)/100;;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(Integer productsCount) {
        this.productsCount = productsCount;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
