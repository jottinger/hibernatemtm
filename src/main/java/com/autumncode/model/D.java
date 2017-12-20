package com.autumncode.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class D {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String value;
}
