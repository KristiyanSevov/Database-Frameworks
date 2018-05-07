package org.softuni.mostwanted.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntriesImportXmlWrapper {
    @XmlElement(name = "race-entry")
    private List<RaceEntryImportXMLDto> entries;

    public RaceEntriesImportXmlWrapper() {
    }

    public List<RaceEntryImportXMLDto> getEntries() {
        return entries;
    }

    public void setEntries(List<RaceEntryImportXMLDto> entries) {
        this.entries = entries;
    }
}
