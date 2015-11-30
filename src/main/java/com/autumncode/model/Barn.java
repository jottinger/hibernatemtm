package com.autumncode.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Barn extends Thing {
    @Column(nullable = false)
    String address;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "barn")
    List<Animal> animals;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Barn{");
        sb.append("address='").append(address).append('\'');
        sb.append(", animals=").append(animals);
        sb.append('}');
        return sb.toString();
    }
}
