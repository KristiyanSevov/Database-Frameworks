package Ex2_Sales_db;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "store_locations")
public class StoreLocation {
    private Integer id;
    private String name;
    private Set<Sale> sales;

    public StoreLocation() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "storeLocation", targetEntity = Sale.class)
    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
