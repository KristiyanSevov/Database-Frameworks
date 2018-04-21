package cardealer.dto.views;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaleDiscountView {
    @Expose
    private PlainCarView car;
    @Expose
    private String customerName;
    @Expose
    @SerializedName("Discount")
    private Double discount;
    @Expose
    private Double price;
    @Expose
    private Double priceWithDiscount;

    public SaleDiscountView() {
    }

    public SaleDiscountView(PlainCarView car,
                            String customerName,
                            Double discount,
                            Double price) {
        this.car = car;
        this.customerName = customerName;
        this.discount = discount;
        this.price = price;
        this.priceWithDiscount = price - price * discount;
    }

    public PlainCarView getCar() {
        return car;
    }

    public void setCar(PlainCarView car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(Double priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
