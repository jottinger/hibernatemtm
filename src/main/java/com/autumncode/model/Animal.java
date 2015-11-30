package com.autumncode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Animal extends Thing {
    @Column(nullable=false)
    String breed;
@ManyToOne
    Barn barn;

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Barn getBarn() {
        return barn;
    }

    public void setBarn(Barn barn) {
        this.barn = barn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Animal{");
        sb.append("breed='").append(breed).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
