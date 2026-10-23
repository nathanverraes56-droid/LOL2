package com.basespringboot.service;

import java.util.List;

import com.basespringboot.dto.ChampDto;
import com.basespringboot.entity.Champ;

public interface ChampService {
    Champ save(ChampDto champ);
    List<Champ> findAll();
    Champ findById(Long id);
    Champ update(Long id, ChampDto formDto);
    Champ patch(Long id, ChampDto formDto);
    void delete(Long id);
}