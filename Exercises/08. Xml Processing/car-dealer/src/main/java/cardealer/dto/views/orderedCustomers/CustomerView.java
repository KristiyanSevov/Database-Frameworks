package cardealer.dto.views.orderedCustomers;

import cardealer.config.DateFormatterAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerView {
    @XmlElement
    private Integer id;
    @XmlElement
    private String name;
    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(DateFormatterAdapter.class)
    private Date date;
    @XmlElement(name = "is-young-driver")
    private Boolean isYoungDriver;

    public CustomerView() {
    }

    public CustomerView(Integer id, String name, Date date, Boolean isYoungDriver) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.isYoungDriver = isYoungDriver;
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
}
