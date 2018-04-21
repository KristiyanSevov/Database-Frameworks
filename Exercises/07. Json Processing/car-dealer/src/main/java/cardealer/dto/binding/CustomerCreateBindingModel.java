package cardealer.dto.binding;

import com.google.gson.annotations.Expose;

import java.time.LocalDate;
import java.util.Date;

public class CustomerCreateBindingModel {
    @Expose
    private String name;
    @Expose
    private Date birthDate;
    @Expose
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
