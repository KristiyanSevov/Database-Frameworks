package org.softuni.mostwanted.models.dto;


import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class DistrictsJSONImportDto {
    @Expose
    @NotNull
    private String name;
    @Expose
    private String townName;

    public DistrictsJSONImportDto() {
    }

    public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTownName() {
            return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
