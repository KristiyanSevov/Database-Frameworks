package softuni.gamestore.models.entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {
    private Long id;
    private String title;
    private String trailer;
    private String imageURL;
    private Double size;
    private BigDecimal price;
    private String description;
    private Date releaseDate;
    private Set<User> users;
    private Set<Order> orders;

    public Game() {
        this.users = new HashSet<>();
        this.orders = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "title")
    @Pattern(regexp = "^[A-Z].{2,99}$")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "trailer")
    @Size(min = 11, max = 11)
        public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Column(name = "image_url")
    @Pattern(regexp = "^(https://.*|http://.*)|null$")
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Column(name = "price", precision = 10, scale = 2)
    @DecimalMin(value = "0.0")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Min(value = 0)
    @Column(name = "size", columnDefinition = "DOUBLE(15,1)")
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Column(name = "description")
    @Size(min = 20)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "release_date")
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @ManyToMany(mappedBy = "games", targetEntity = User.class)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
            this.users = users;
    }

    @ManyToMany(mappedBy = "games", targetEntity = Order.class)
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
