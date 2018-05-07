package org.softuni.mostwanted.models.dto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceImportXMLDto {
    @XmlElement
    private Integer laps;
    @XmlElement(name = "district-name")
    @NotNull
    private String districtName;
    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<EntryImportXmlDto> entries;

    public RaceImportXMLDto() {
    }

    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<EntryImportXmlDto> getEntries() {
        return entries;
    }

    public void setEntries(List<EntryImportXmlDto> entries) {
        this.entries = entries;
    }
}
