package com.autumncode.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class C {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String value;
    @OneToMany(cascade = CascadeType.ALL)
    Set<D> dSet;
}
