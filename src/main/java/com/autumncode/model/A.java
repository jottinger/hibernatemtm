package com.autumncode.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class A {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String value;
    @OneToOne
    B b;

}
