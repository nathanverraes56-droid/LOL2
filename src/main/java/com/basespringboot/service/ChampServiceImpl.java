package com.basespringboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.basespringboot.dto.ChampDto;
import com.basespringboot.entity.Champ;
import com.basespringboot.repository.ChampRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@Transactional 
@RequiredArgsConstructor 
public class ChampServiceImpl implements ChampService{

    private final ChampRepository champRepository;

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
        return champRepository.save(champ);
    }

    @Override
    public Champ patch(Long id, ChampDto formDto) {
        Champ champ = findById(id);
        // Mise à jour partielle (PATCH) : vérification de la présence des données
        if (formDto.getNom() != null && !formDto.getNom().isBlank()) champ.setNom(formDto.getNom());
        if (formDto.getSexe() != null && !formDto.getSexe().isBlank()) champ.setSexe(formDto.getSexe());
        if (formDto.getRole() != null && !formDto.getRole().isBlank()) champ.setRole(formDto.getRole());
        if (formDto.getEspece() != null && !formDto.getEspece().isBlank()) champ.setEspece(formDto.getEspece());
        if (formDto.getRessource() != null && !formDto.getRessource().isBlank()) champ.setRessource(formDto.getRessource());
        if (formDto.getPortee() != null && !formDto.getPortee().isBlank()) champ.setPortee(formDto.getPortee());
        if (formDto.getRegion() != null && !formDto.getRegion().isBlank()) champ.setRegion(formDto.getRegion());
        // L'attribut annee étant un type primitif (int), sa valeur par défaut est 0 si non fournie dans la requête
        if (formDto.getAnnee() != 0) champ.setAnnee(formDto.getAnnee()); 
        
        return champRepository.save(champ);
    }

    @Override
    public void delete(Long id) {
        Champ champ = findById(id);
        champRepository.delete(champ);
    }
}
