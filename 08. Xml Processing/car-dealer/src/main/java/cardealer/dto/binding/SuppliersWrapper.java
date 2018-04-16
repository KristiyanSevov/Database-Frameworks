package cardealer.dto.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuppliersWrapper {
    @XmlElement(name = "supplier")
    private List<SupplierCreateBindingModel> suppliers;

    public SuppliersWrapper() {
    }

    public SuppliersWrapper(List<SupplierCreateBindingModel> suppliers) {
        this.suppliers = suppliers;
    }

    public List<SupplierCreateBindingModel> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierCreateBindingModel> suppliers) {
        this.suppliers = suppliers;
    }
}
