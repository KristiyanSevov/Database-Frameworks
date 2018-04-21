package app.exam.domain.dto.xml;

import app.exam.config.DateFormatterAdapter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderXMLImportDTO{
    @XmlElement
    @NotNull
    private String customer;
    @XmlElement
    @NotNull
    @Size(min = 3, max = 30)
    private String employee;
    @XmlElement
    @NotNull
    @XmlJavaTypeAdapter(DateFormatterAdapter.class)
    private Date date;
    @XmlElement
    private String type;
    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<OrderItemXMLImportDTO> items;

    public OrderXMLImportDTO() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OrderItemXMLImportDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemXMLImportDTO> items) {
        this.items = items;
    }
}
