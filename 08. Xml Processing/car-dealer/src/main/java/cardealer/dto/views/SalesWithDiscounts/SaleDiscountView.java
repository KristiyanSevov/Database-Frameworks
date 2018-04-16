package cardealer.dto.views.SalesWithDiscounts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleDiscountView {
    @XmlElement(name = "car")
    private PlainCarView car;
    @XmlElement(name = "customer-name")
    private String customerName;
    @XmlElement
    private Double discount;
    @XmlElement
    private Double price;
    @XmlElement(name = "price-with-discount")
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
