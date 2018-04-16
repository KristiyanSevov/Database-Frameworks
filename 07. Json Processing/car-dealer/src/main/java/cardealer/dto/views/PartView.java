package cardealer.dto.views;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;

public class PartView implements Serializable {
    @Expose
    @SerializedName("Name")
    private String name;
    @Expose
    @SerializedName("Price")
    private BigDecimal price;

    public PartView() {
    }

    public PartView(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
