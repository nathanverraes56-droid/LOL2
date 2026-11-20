package com.basespringboot.service;

import com.basespringboot.dto.ChampDto;
import com.basespringboot.entity.Champ;
import com.basespringboot.entity.Equipe;
import com.basespringboot.repository.ChampRepository;
import com.basespringboot.repository.EquipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChampServiceImplTest {

    @Mock
    private ChampRepository champRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private ChampServiceImpl champService;

    private Equipe equipe;
    private ChampDto champDto;

    @BeforeEach
    void setUp() {
        equipe = new Equipe();
        equipe.setId(1L);
        equipe.setNom("T1");
        equipe.setChampions(new ArrayList<>());

        champDto = new ChampDto();
        champDto.setNom("Ahri");
        champDto.setSexe("Femme");
        champDto.setRole("Mage");
        champDto.setEspece("Vastaya");
        champDto.setRessource("Mana");
        champDto.setPortee("Distance");
        champDto.setRegion("Ionia");
        champDto.setAnnee(2011);
        champDto.setEquipeId(1L);
    }

    @Test
    void save_ShouldSaveChampion_WhenTeamHasLessThan5Champions() {
        // Arrange
        when(equipeRepository.findById(1L)).thenReturn(Optional.of(equipe));
        when(champRepository.save(any(Champ.class))).thenAnswer(invocation -> {
            Champ c = invocation.getArgument(0);
            c.setId(10L);
            return c;
        });

        // Act
        Champ result = champService.save(champDto);

        // Assert
        assertNotNull(result);
        assertEquals("Ahri", result.getNom());
        assertEquals(equipe, result.getEquipe());
        verify(champRepository, times(1)).save(any(Champ.class));
    }

    @Test
    void save_ShouldThrowException_WhenTeamAlreadyHas5Champions() {
        // Arrange : On remplit l'équipe avec 5 champions fictifs
        for (int i = 0; i < 5; i++) {
            equipe.getChampions().add(new Champ());
        }
        when(equipeRepository.findById(1L)).thenReturn(Optional.of(equipe));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> champService.save(champDto));
        assertTrue(exception.getMessage().contains("limit of 5 players"));
        verify(champRepository, never()).save(any(Champ.class));
    }

    @Test
    void findById_ShouldReturnChampion_WhenExists() {
        Champ champ = new Champ();
        champ.setId(1L);
        champ.setNom("Garen");
        when(champRepository.findById(1L)).thenReturn(Optional.of(champ));

        Champ found = champService.findById(1L);

        assertEquals("Garen", found.getNom());
    }

    @Test
    void findById_ShouldThrowException_WhenNotFound() {
        when(champRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> champService.findById(99L));
    }

    @Test
    void patch_ShouldUpdateOnlyProvidedFields() {
        Champ existingChamp = new Champ();
        existingChamp.setId(1L);
        existingChamp.setNom("Garen");
        existingChamp.setRole("Combattant");
        existingChamp.setRegion("Demacia");

        ChampDto patchDto = new ChampDto();
        patchDto.setRole("Tank"); // On ne modifie que le rôle

        when(champRepository.findById(1L)).thenReturn(Optional.of(existingChamp));
        when(champRepository.save(any(Champ.class))).thenAnswer(i -> i.getArgument(0));

        Champ updated = champService.patch(1L, patchDto);

        assertEquals("Garen", updated.getNom()); // Inchangé
        assertEquals("Tank", updated.getRole()); // Modifié
        assertEquals("Demacia", updated.getRegion()); // Inchangé
    }

    @Test
    void delete_ShouldCallRepositoryDelete_WhenExists() {
        Champ champ = new Champ();
        champ.setId(1L);
        when(champRepository.findById(1L)).thenReturn(Optional.of(champ));

        champService.delete(1L);

        verify(champRepository, times(1)).delete(champ);
    }
}