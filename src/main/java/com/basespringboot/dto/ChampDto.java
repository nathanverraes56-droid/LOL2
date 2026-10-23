package com.basespringboot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class ChampDto {

    @NotBlank 
    private String nom;
    private String sexe;
    private String role;
    private String espece;
    private String ressource;
    private String portee;
    private String region;
    private int annee;

    private Long equipeId;
    
}
