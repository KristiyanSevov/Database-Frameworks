package cardealer.dto.views.carsToyota;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarExportWrapper {
    @XmlElement(name = "car")
    private List<CarView> cars;

    public CarExportWrapper() {
    }

    public CarExportWrapper(List<CarView> cars) {
        this.cars = cars;
    }

    public List<CarView> getCars() {
        return cars;
    }

    public void setCars(List<CarView> cars) {
        this.cars = cars;
    }
}
