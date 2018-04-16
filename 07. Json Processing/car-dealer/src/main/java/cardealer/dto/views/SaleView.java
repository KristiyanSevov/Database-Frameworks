package cardealer.dto.views;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SaleView implements Serializable{
    @Expose
    @SerializedName("saleId")
    private Integer id;

    public SaleView() {
    }

    public SaleView(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
