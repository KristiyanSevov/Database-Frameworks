package cardealer.dto.views.localSuppliers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierExportWrapper {
    @XmlElement(name = "supplier")
    private List<SupplierView> suppliers;

    public SupplierExportWrapper() {
    }

    public SupplierExportWrapper(List<SupplierView> suppliers) {
        this.suppliers = suppliers;
    }

    public List<SupplierView> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierView> suppliers) {
        this.suppliers = suppliers;
    }
}
