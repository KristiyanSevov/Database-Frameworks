package Ex4_Hospital_db;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medicaments")
public class Medicament {
    private Integer id;
    private String name;
    private Set<Patient> patients;

    public Medicament() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "medicaments", targetEntity = Patient.class)
    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
