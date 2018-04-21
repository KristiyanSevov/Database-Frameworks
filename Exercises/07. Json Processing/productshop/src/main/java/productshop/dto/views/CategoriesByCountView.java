package productshop.dto.views;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class CategoriesByCountView implements Serializable {
    @Expose
    private String category;
    @Expose
    private Integer productsCount;
    @Expose
    private Double averagePrice;
    @Expose
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
