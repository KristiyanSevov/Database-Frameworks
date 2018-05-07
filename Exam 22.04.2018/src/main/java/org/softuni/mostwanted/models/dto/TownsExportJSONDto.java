package org.softuni.mostwanted.models.dto;

import com.google.gson.annotations.Expose;

public class TownsExportJSONDto {
    @Expose
    private String name;
    @Expose
    private Integer racers;

    public TownsExportJSONDto() {
    }

    public TownsExportJSONDto(String name, Integer racers) {
        this.name = name;
        this.racers = racers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRacers() {
        return racers;
    }

    public void setRacers(Integer racers) {
        this.racers = racers;
    }
}
