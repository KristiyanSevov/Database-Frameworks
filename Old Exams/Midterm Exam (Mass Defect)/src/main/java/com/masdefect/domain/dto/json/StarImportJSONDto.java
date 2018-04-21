package com.masdefect.domain.dto.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class StarImportJSONDto implements Serializable {
    @Expose
    private String name;
    @Expose
    @SerializedName("solarSystem")
    private String solarSystem;

    public StarImportJSONDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSolarSystem() {
        return solarSystem;
    }

    public void setSolarSystem(String solarSystem) {
        this.solarSystem = solarSystem;
    }
}
