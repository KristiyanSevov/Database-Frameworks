package app.retake.domain.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "procedure")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureXMLExportDTO {
    @XmlAttribute(name = "animal-passport")
    private String serialNumber;
    @XmlElement(name = "owner")
    private String ownerPhoneNumber;
    @XmlElementWrapper(name = "animal-aids")
    @XmlElement(name = "animal-aid")
    private List<ProcedureAnimalAidXMLExportDTO> animalAids;

    public ProcedureXMLExportDTO() {
    }

    public ProcedureXMLExportDTO(String serialNumber, String ownerPhoneNumber, List<ProcedureAnimalAidXMLExportDTO> animalAids) {
        this.serialNumber = serialNumber;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.animalAids = animalAids;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public List<ProcedureAnimalAidXMLExportDTO> getAnimalAids() {
        return animalAids;
    }

    public void setAnimalAids(List<ProcedureAnimalAidXMLExportDTO> animalAids) {
        this.animalAids = animalAids;
    }
}
