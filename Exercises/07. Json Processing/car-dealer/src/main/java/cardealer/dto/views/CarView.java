package cardealer.dto.views;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarView extends PlainCarView {
    @Expose
    @SerializedName("Id")
    private Integer id;

    public CarView() {
    }

    public CarView(Integer id, String make, String model, Long travelledDistance) {
        super(make, model, travelledDistance);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
