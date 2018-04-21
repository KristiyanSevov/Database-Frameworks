package productshop.dto.binding;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CategoryCreateBindingModel implements Serializable {
    @Expose
    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    public CategoryCreateBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
