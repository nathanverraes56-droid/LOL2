package com.basespringboot.service;

import java.util.List;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import com.basespringboot.dto.ChampDto;
import com.basespringboot.entity.Champ;
import com.basespringboot.entity.Equipe;
import com.basespringboot.repository.ChampRepository;
import com.basespringboot.repository.EquipeRepository;

@Service 
@Transactional 
@RequiredArgsConstructor 
public class ChampServiceImpl implements ChampService {

    private final ChampRepository champRepository;
    private final EquipeRepository equipeRepository; 

    @Override 
    public Champ save(ChampDto champDto) {
        Champ champ = new Champ();
        champ.setNom(champDto.getNom());
        champ.setSexe(champDto.getSexe());
        champ.setRole(champDto.getRole());
        champ.setEspece(champDto.getEspece());
        champ.setRessource(champDto.getRessource());
        champ.setPortee(champDto.getPortee());
        champ.setRegion(champDto.getRegion());
        champ.setAnnee(champDto.getAnnee());
        
        if (champDto.getEquipeId() != null) {
            Equipe equipe = equipeRepository.findById(champDto.getEquipeId())
                    .orElseThrow(() -> new RuntimeException("Equipe introuvable avec l'ID : " + champDto.getEquipeId()));
            
            if (equipe.getChampions().size() >= 5) {
                throw new RuntimeException("Impossible d'ajouter le champion : l'équipe a déjà atteint la limite de 5 joueurs.");
            }
            champ.setEquipe(equipe);
        }

        return champRepository.save(champ);
    }

    @Override
    public List<Champ> findAll() {
        return champRepository.findAll();
    }

    @Override
    public Champ findById(Long id) {
        return champRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Champ introuvable avec l'ID : " + id));
    }

    @Override
    public Champ update(Long id, ChampDto formDto) {
        Champ champ = findById(id);
        
        // Remplacement intégral (PUT)
        champ.setNom(formDto.getNom());
        champ.setSexe(formDto.getSexe());
        champ.setRole(formDto.getRole());
        champ.setEspece(formDto.getEspece());
        champ.setRessource(formDto.getRessource());
        champ.setPortee(formDto.getPortee());
        champ.setRegion(formDto.getRegion());
        champ.setAnnee(formDto.getAnnee());

        if (formDto.getEquipeId() != null) {
            Equipe equipe = equipeRepository.findById(formDto.getEquipeId())
                    .orElseThrow(() -> new RuntimeException("Equipe introuvable avec l'ID : " + formDto.getEquipeId()));
            
            // On vérifie la limite de la nouvelle équipe uniquement si le champion n'y était pas déjà
            if ((champ.getEquipe() == null || !champ.getEquipe().getId().equals(equipe.getId())) 
                && equipe.getChampions().size() >= 5) {
                throw new RuntimeException("L'équipe cible est déjà complète (5 champions).");
            }
            champ.setEquipe(equipe);
        } else {
            champ.setEquipe(null); // Retire le champion de l'équipe si l'ID n'est pas fourni dans la requête
        }

        return champRepository.save(champ);
    }

    @Override
    public Champ patch(Long id, ChampDto formDto) {
        Champ champ = findById(id);
        
        // Mise à jour partielle (PATCH)
        if (formDto.getNom() != null && !formDto.getNom().isBlank()) champ.setNom(formDto.getNom());
        if (formDto.getSexe() != null && !formDto.getSexe().isBlank()) champ.setSexe(formDto.getSexe());
        if (formDto.getRole() != null && !formDto.getRole().isBlank()) champ.setRole(formDto.getRole());
        if (formDto.getEspece() != null && !formDto.getEspece().isBlank()) champ.setEspece(formDto.getEspece());
        if (formDto.getRessource() != null && !formDto.getRessource().isBlank()) champ.setRessource(formDto.getRessource());
        if (formDto.getPortee() != null && !formDto.getPortee().isBlank()) champ.setPortee(formDto.getPortee());
        if (formDto.getRegion() != null && !formDto.getRegion().isBlank()) champ.setRegion(formDto.getRegion());
        if (formDto.getAnnee() != 0) champ.setAnnee(formDto.getAnnee()); 
        
        if (formDto.getEquipeId() != null) {
            Equipe equipe = equipeRepository.findById(formDto.getEquipeId())
                    .orElseThrow(() -> new RuntimeException("Equipe introuvable avec l'ID : " + formDto.getEquipeId()));
            
            if ((champ.getEquipe() == null || !champ.getEquipe().getId().equals(equipe.getId())) 
                && equipe.getChampions().size() >= 5) {
                throw new RuntimeException("L'équipe cible est déjà complète (5 champions).");
            }
            champ.setEquipe(equipe);
        }
        
        return champRepository.save(champ);
    }

    @Override
    public void delete(Long id) {
        Champ champ = findById(id);
        champRepository.delete(champ);
    }
}