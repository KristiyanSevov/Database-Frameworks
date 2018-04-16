package cardealer.dto.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartsWrapper {
    @XmlElement(name = "part")
    private List<PartCreateBindingModel> parts;

    public PartsWrapper() {
    }

    public PartsWrapper(List<PartCreateBindingModel> parts) {
        this.parts = parts;
    }

    public List<PartCreateBindingModel> getParts() {
        return parts;
    }

    public void setParts(List<PartCreateBindingModel> parts) {
        this.parts = parts;
    }
}
