package org.softuni.mostwanted.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "most-wanted")
@XmlAccessorType(XmlAccessType.FIELD)
public class RacerExportXMLWrapper {
    @XmlElement(name = "racer")
    private List<RacerExportXmlDto> racers;

    public RacerExportXMLWrapper() {
        this.racers = new ArrayList<>();
    }

    public List<RacerExportXmlDto> getRacers() {
        return racers;
    }

    public void setRacers(List<RacerExportXmlDto> racers) {
        this.racers = racers;
    }
}
