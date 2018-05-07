package org.softuni.mostwanted.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryExportXMLDto {
    @XmlElement(name = "finish-time")
    private Double finishTime;
    @XmlElement
    private String car;

    public EntryExportXMLDto() {
    }

    public EntryExportXMLDto(Double finishTime, String car) {
        this.finishTime = finishTime;
        this.car = car;
    }

    public Double getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Double finishTime) {
        this.finishTime = finishTime;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}
