package com.basespringboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    // The mappedBy attribute indicates that the relationship is managed by the "equipe" field in the Champ class
    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Champ> champions = new ArrayList<>();
}