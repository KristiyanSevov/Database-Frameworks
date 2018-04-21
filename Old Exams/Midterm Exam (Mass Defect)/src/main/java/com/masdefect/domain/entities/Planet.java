package com.masdefect.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Planet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(optional = false)
    private Star sun;

    @ManyToOne(optional = false)
    private SolarSystem solarSystem;

    @OneToMany(mappedBy = "homePlanet")
    private Set<Person> people;

    @OneToMany(mappedBy = "originPlanet")
    private Set<Anomaly> originAnomalies;

    @OneToMany(mappedBy = "teleportPlanet")
    private Set<Anomaly> teleportAnomalies;

    public Planet() {
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

    public Star getSun() {
        return sun;
    }

    public void setSun(Star sun) {
        this.sun = sun;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    public void setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }

    public Set<Anomaly> getOriginAnomalies() {
        return originAnomalies;
    }

    public void setOriginAnomalies(Set<Anomaly> originAnomalies) {
        this.originAnomalies = originAnomalies;
    }

    public Set<Anomaly> getTeleportAnomalies() {
        return teleportAnomalies;
    }

    public void setTeleportAnomalies(Set<Anomaly> teleportAnomalies) {
        this.teleportAnomalies = teleportAnomalies;
    }
}
