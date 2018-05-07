package org.softuni.mostwanted.models.dto;

import com.google.gson.annotations.Expose;

public class CarExportJSONDto {
    @Expose
    private String car;

    public CarExportJSONDto() {
    }

    public CarExportJSONDto(String car) {
        this.car = car;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}
