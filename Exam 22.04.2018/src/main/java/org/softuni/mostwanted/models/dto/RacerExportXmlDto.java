package org.softuni.mostwanted.models.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "racer")
@XmlAccessorType(XmlAccessType.FIELD)
public class RacerExportXmlDto {
    @XmlAttribute
    private String name;
    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<EntryExportXMLDto> entries;

    public RacerExportXmlDto() {
    }

    public RacerExportXmlDto(String name, List<EntryExportXMLDto> entries) {
        this.name = name;
        this.entries = entries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EntryExportXMLDto> getEntries() {
        return entries;
    }

    public void setEntries(List<EntryExportXMLDto> entries) {
        this.entries = entries;
    }
}
