package com.basespringboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Champ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String sexe;
    private String role;
    private String espece;
    private String ressource;
    private String portee;
    private String region;
    private int annee;
    
    // --- NOUVEAUX CHAMPS AJOUTÉS ---
    @ManyToOne
    @JoinColumn(name = "equipe_id")
    @JsonIgnore // Empêche la boucle infinie (Equipe -> Champ -> Equipe -> ...) en JSON
    private Equipe equipe;
}
