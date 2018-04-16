package cardealer.dto.views.carsWithParts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarWithPartsWrapper {
    @XmlElement(name = "car")
    private List<CarWithPartsView> cars;

    public CarWithPartsWrapper() {
    }

    public CarWithPartsWrapper(List<CarWithPartsView> cars) {
        this.cars = cars;
    }

    public List<CarWithPartsView> getCars() {
        return cars;
    }

    public void setCars(List<CarWithPartsView> cars) {
        this.cars = cars;
    }
}
