package com.masdefect.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Anomaly implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    private Planet originPlanet;

    @ManyToOne(optional = false)
    private Planet teleportPlanet;

    @ManyToMany
    private Set<Person> victims;

    public Anomaly() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Planet getOriginPlanet() {
        return originPlanet;
    }

    public void setOriginPlanet(Planet originPlanet) {
        this.originPlanet = originPlanet;
    }

    public Planet getTeleportPlanet() {
        return teleportPlanet;
    }

    public void setTeleportPlanet(Planet teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }

    public Set<Person> getVictims() {
        return victims;
    }

    public void setVictims(Set<Person> victims) {
        this.victims = victims;
    }
}
