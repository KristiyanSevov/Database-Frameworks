package app.retake.domain.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "procedures")
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany(mappedBy = "procedures")
    private Set<AnimalAid> services;
    @Transient
    private BigDecimal cost;
    @ManyToOne
    @JoinColumn(name = "vet_id", referencedColumnName = "id")
    private Vet vet;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    private Animal animal;

    public Procedure() {
        this.services = new HashSet<>();
    }

    public Procedure(Vet vet, Animal animal, Set<AnimalAid> services) {
        this.vet = vet;
        this.animal = animal;
        this.services = services;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<AnimalAid> getServices() {
        return services;
    }

    public void setServices(Set<AnimalAid> services) {
        this.services = services;
    }

    public BigDecimal getCost() {
        return BigDecimal.valueOf(this.services.stream().mapToDouble(s -> s.getPrice().doubleValue()).sum());
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
