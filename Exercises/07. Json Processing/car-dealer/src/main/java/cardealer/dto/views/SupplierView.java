package cardealer.dto.views;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SupplierView implements Serializable {
    @Expose
    @SerializedName("Id")
    private Integer id;
    @Expose
    @SerializedName("Name")
    private String name;
    @Expose
    private Integer partsCount;

    public SupplierView() {
    }

    public SupplierView(Integer id, String name, Integer partsCount) {
        this.id = id;
        this.name = name;
        this.partsCount = partsCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(Integer partsCount) {
        this.partsCount = partsCount;
    }
}
