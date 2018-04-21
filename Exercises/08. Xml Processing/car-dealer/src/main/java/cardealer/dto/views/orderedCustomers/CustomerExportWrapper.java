package cardealer.dto.views.orderedCustomers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerExportWrapper {
    @XmlElement(name = "customer")
    private List<CustomerView> customers;

    public CustomerExportWrapper() {
    }

    public CustomerExportWrapper(List<CustomerView> customers) {
        this.customers = customers;
    }

    public List<CustomerView> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerView> customers) {
        this.customers = customers;
    }
}
