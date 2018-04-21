package cardealer.dto.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsWrapper {
    @XmlElement(name = "car")
    private List<CarCreateBindingModel> cars;

    public CarsWrapper() {
    }

    public CarsWrapper(List<CarCreateBindingModel> cars) {
        this.cars = cars;
    }

    public List<CarCreateBindingModel> getCars() {
        return cars;
    }

    public void setCars(List<CarCreateBindingModel> cars) {
        this.cars = cars;
    }
}
