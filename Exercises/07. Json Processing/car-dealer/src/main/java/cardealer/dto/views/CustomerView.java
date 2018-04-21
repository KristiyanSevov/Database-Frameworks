package cardealer.dto.views;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CustomerView implements Serializable{
    @Expose
    @SerializedName("Id")
    private Integer id;
    @Expose
    @SerializedName("Name")
    private String name;
    @Expose
    @SerializedName("BirthDate")
    private Date date;
    @Expose
    @SerializedName("IsYoungDriver")
    private Boolean isYoungDriver;
    @Expose
    @SerializedName("Sales")
    private List<SaleView> sales;

    public CustomerView() {
    }

    public CustomerView(Integer id, String name, Date date, Boolean isYoungDriver, List<SaleView> sales) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.isYoungDriver = isYoungDriver;
        this.sales = sales;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public List<SaleView> getSales() {
        return sales;
    }

    public void setSales(List<SaleView> sales) {
        this.sales = sales;
    }
}
