package softuni.gamestore.models.dtos.bindingModels;

import java.math.BigDecimal;
import java.util.Date;

public class GameRegisterBindingModel {
    private String title;
    private String trailer;
    private String imageURL;
    private Double size;
    private BigDecimal price;
    private String description;
    private Date releaseDate;

    public GameRegisterBindingModel() {
    }

    public GameRegisterBindingModel(String title, BigDecimal price, Double size, String trailer,
                                    String imageURL, String description, Date releaseDate) {
        this.title = title;
        this.trailer = trailer;
        this.imageURL = imageURL;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
