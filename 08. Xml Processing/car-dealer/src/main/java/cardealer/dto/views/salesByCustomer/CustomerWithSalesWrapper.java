package cardealer.dto.views.salesByCustomer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerWithSalesWrapper {
    @XmlElement(name = "customer")
    private List<CustomerWithSalesView> customers;

    public CustomerWithSalesWrapper() {
    }

    public CustomerWithSalesWrapper(List<CustomerWithSalesView> customers) {
        this.customers = customers;
    }

    public List<CustomerWithSalesView> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerWithSalesView> customers) {
        this.customers = customers;
    }
}
