package app.exam.domain.dto.json;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ItemJSONImportDTO {
    @Size(min = 3, max = 30)
    @NotNull
    @Expose
    private String name;
    @DecimalMin("0.01")
    @NotNull
    @Expose
    private BigDecimal price;
    @NotNull
    @Size(min = 3, max = 30)
    @Expose
    private String category;

    public ItemJSONImportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
