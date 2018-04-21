package app.exam.domain.dto.json;

import com.google.gson.annotations.Expose;

import java.util.List;

public class EmployeeOrdersJSONExportDTO {
    @Expose
    private String employeeName;
    @Expose
    private List<OrderJSONExportDTO> orders;

    public EmployeeOrdersJSONExportDTO() {
    }

    public EmployeeOrdersJSONExportDTO(String employeeName, List<OrderJSONExportDTO> orders) {
        this.employeeName = employeeName;
        this.orders = orders;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public List<OrderJSONExportDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderJSONExportDTO> orders) {
        this.orders = orders;
    }
}
