package app.retake.domain.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AnimalsJSONExportDTO implements Serializable {
    @Expose
    private String ownerName;
    @Expose
    private String animalName;
    @Expose
    private Integer age;
    @Expose
    private String serialNumber;
    @Expose
    private String registeredOn;

    public AnimalsJSONExportDTO() {
    }

    public AnimalsJSONExportDTO(String ownerName,
                                String animalName,
                                Integer age,
                                String serialNumber,
                                Date registeredOn) {
        this.ownerName = ownerName;
        this.animalName = animalName;
        this.age = age;
        this.serialNumber = serialNumber;
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        this.registeredOn = df.format(registeredOn);
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(String registeredOn) {
        this.registeredOn = registeredOn;
    }
}
