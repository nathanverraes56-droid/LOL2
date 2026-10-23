package com.basespringboot.service;

import com.basespringboot.dto.EquipeDto;
import com.basespringboot.entity.Equipe;
import com.basespringboot.repository.EquipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EquipeServiceImpl implements EquipeService {

    private final EquipeRepository equipeRepository;

    @Override
    public Equipe save(EquipeDto equipeDto) {
        Equipe equipe = new Equipe();
        equipe.setNom(equipeDto.getNom());
        return equipeRepository.save(equipe);
    }

    @Override
    public List<Equipe> findAll() {
        return equipeRepository.findAll();
    }

    @Override
    public Equipe findById(Long id) {
        return equipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Équipe introuvable avec l'ID : " + id));
    }
}