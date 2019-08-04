package com.autumncode.exonity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
public class Customer1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    Long id;
    @Getter
    @Setter
    String name;
    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL)
    Set<Document1> documents=new HashSet<>();

    public void addDocument(String name) {
        Document1 d=new Document1();
        d.setName(name);
        documents.add(d);
    }
}
