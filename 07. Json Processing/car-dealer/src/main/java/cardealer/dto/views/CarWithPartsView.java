package cardealer.dto.views;

import com.google.gson.annotations.Expose;

import java.util.List;

public class CarWithPartsView {
    @Expose
    private PlainCarView car;
    @Expose
    private List<PartView> parts;

    public CarWithPartsView() {
    }

    public CarWithPartsView(PlainCarView car, List<PartView> parts) {
        this.car = car;
        this.parts = parts;
    }

    public PlainCarView getCar() {
        return car;
    }

    public void setCar(PlainCarView car) {
        this.car = car;
    }

    public List<PartView> getParts() {
        return parts;
    }

    public void setParts(List<PartView> parts) {
        this.parts = parts;
    }
}
