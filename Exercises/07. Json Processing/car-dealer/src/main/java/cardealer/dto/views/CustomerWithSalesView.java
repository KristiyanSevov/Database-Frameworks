package cardealer.dto.views;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class CustomerWithSalesView implements Serializable {
    @Expose
    private String fullName;
    @Expose
    private Integer boughtCars;
    @Expose
    private Double spentMoney;

    public CustomerWithSalesView() {
    }

    public CustomerWithSalesView(String fullName, Integer boughtCars, Double spentMoney) {
        this.fullName = fullName;
        this.boughtCars = boughtCars;
        this.spentMoney = (double)Math.round(spentMoney * 100) / 100;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(Integer boughtCars) {
        this.boughtCars = boughtCars;
    }

    public Double getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(Double spentMoney) {
        this.spentMoney = spentMoney;
    }
}
