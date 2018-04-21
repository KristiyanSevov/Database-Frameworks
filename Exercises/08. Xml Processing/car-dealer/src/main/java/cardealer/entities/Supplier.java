package cardealer.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "suppliers")
public class Supplier {
    private Integer id;
    private String name;
    private Boolean isImporter;
    private Set<Part> parts;

    public Supplier() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Boolean getIsImporter() {
        return isImporter;
    }

    public void setIsImporter(Boolean isImporter) {
        this.isImporter = isImporter;
    }

    @OneToMany(mappedBy = "supplier")
    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
