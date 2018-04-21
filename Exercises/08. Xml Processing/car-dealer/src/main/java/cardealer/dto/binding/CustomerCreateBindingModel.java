package cardealer.dto.binding;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.Date;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerCreateBindingModel {
    @XmlAttribute
    private String name;
    @XmlElement(name = "birth-date")
    private Date birthDate;
    @XmlElement(name = "is-young-driver")
    private Boolean isYoungDriver;

    public CustomerCreateBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
