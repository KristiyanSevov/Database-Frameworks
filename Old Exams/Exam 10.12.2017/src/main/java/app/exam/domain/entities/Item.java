package app.exam.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @ManyToOne(optional = false)
    private Category category;
    @Column(nullable = false)
    private BigDecimal price;
    @OneToMany(mappedBy = "item")
    private Set<OrderItem> orderItems;

    public Item() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item(String name, Category category, BigDecimal price, Set<OrderItem> orderItems) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.orderItems = orderItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
