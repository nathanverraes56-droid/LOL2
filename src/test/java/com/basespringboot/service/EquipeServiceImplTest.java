package com.basespringboot.service;

import com.basespringboot.dto.EquipeDto;
import com.basespringboot.entity.Equipe;
import com.basespringboot.repository.EquipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EquipeServiceImplTest {

    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @Test
    void save_ShouldCreateAndReturnEquipe() {
        EquipeDto dto = new EquipeDto();
        dto.setNom("Fnatic");

        when(equipeRepository.save(any(Equipe.class))).thenAnswer(i -> {
            Equipe e = i.getArgument(0);
            e.setId(1L);
            return e;
        });

        Equipe saved = equipeService.save(dto);

        assertNotNull(saved);
        assertEquals("Fnatic", saved.getNom());
        verify(equipeRepository, times(1)).save(any(Equipe.class));
    }

    @Test
    void findAll_ShouldReturnListOfEquipes() {
        Equipe e1 = new Equipe();
        e1.setNom("T1");
        Equipe e2 = new Equipe();
        e2.setNom("G2");

        when(equipeRepository.findAll()).thenReturn(List.of(e1, e2));

        List<Equipe> result = equipeService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void findById_ShouldThrowException_WhenNotFound() {
        when(equipeRepository.findById(42L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> equipeService.findById(42L));
    }
}
