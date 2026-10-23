package com.basespringboot.service;

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
}
