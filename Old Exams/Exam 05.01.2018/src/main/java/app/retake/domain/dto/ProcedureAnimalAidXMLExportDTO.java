package app.retake.domain.dto;


import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "animal-aid")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureAnimalAidXMLExportDTO {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private Double price;

    public ProcedureAnimalAidXMLExportDTO() {
    }

    public ProcedureAnimalAidXMLExportDTO(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
