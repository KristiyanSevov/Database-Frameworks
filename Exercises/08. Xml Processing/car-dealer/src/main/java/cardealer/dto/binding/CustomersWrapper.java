package cardealer.dto.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersWrapper {
    @XmlElement(name = "customer")
    private List<CustomerCreateBindingModel> customers;

    public CustomersWrapper() {
    }

    public CustomersWrapper(List<CustomerCreateBindingModel> customers) {
        this.customers = customers;
    }

    public List<CustomerCreateBindingModel> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerCreateBindingModel> customers) {
        this.customers = customers;
    }
}
