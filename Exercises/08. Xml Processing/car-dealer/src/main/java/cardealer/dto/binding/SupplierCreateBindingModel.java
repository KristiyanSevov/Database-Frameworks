package cardealer.dto.binding;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierCreateBindingModel implements Serializable{
    @XmlAttribute
    private String name;
    @XmlAttribute(name = "is-importer")
    private Boolean isImporter;

    public SupplierCreateBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsImporter() {
        return isImporter;
    }

    public void setIsImporter(Boolean importer) {
        isImporter = importer;
    }
}
