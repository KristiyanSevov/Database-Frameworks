package org.softuni.mostwanted.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RacesImportXmlWrapper {
    @XmlElement(name = "race")
    private List<RaceImportXMLDto> races;

    public RacesImportXmlWrapper() {
    }

    public List<RaceImportXMLDto> getRaces() {
        return races;
    }

    public void setRaces(List<RaceImportXMLDto> races) {
        this.races = races;
    }
}
