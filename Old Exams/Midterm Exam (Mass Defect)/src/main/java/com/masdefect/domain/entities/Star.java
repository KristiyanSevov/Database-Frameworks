package com.masdefect.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Star implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "sun")
    private Set<Planet> planets;

    @ManyToOne(optional = false)
    private SolarSystem solarSystem;

    public Star() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(Set<Planet> planets) {
        this.planets = planets;
    }

    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    public void setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }
}
