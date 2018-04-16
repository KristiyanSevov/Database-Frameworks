package cardealer.dto.views.SalesWithDiscounts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesDiscountsExportWrapper {
    @XmlElement(name = "sale")
    private List<SaleDiscountView> sales;

    public SalesDiscountsExportWrapper() {
    }

    public SalesDiscountsExportWrapper(List<SaleDiscountView> sales) {
        this.sales = sales;
    }

    public List<SaleDiscountView> getSales() {
        return sales;
    }

    public void setSales(List<SaleDiscountView> sales) {
        this.sales = sales;
    }
}
