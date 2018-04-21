package cardealer.entities;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale {
    private Integer id;
    private Double discount;
    private Car car;
    private Customer customer;

    public Sale() {
    }

    public Sale(Car car, Customer customer, Double discount) {
        this.car = car;
        this.customer = customer;
        this.discount = discount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(columnDefinition = "DOUBLE(5,2)")
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @OneToOne
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
