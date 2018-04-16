package cardealer.dto.binding;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class CarCreateBindingModel implements Serializable {
    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private String travelledDistance;

    public CarCreateBindingModel() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(String travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
