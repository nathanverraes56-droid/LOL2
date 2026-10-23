package com.basespringboot.service;

import com.basespringboot.dto.EquipeDto;
import com.basespringboot.entity.Equipe;
import java.util.List;

public interface EquipeService {
    Equipe save(EquipeDto equipeDto);
    List<Equipe> findAll();
    Equipe findById(Long id);
}