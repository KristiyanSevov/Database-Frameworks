package org.softuni.mostwanted.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class TownsJSONImportDto {
    @Expose
    @NotNull
    private String name;

    public TownsJSONImportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
