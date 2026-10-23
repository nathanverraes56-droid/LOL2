package com.basespringboot.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.basespringboot.dto.ChampDto;
import com.basespringboot.entity.Champ;
import com.basespringboot.service.ChampService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/champ")
@RestController 
@RequiredArgsConstructor 
public class ChampController {
    private final ChampService champS;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Champ save(@Valid @RequestBody ChampDto champ){
     return champS.save(champ);
    }

   // GET : Lecture (Liste)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Champ> findAll() {
        return champS.findAll();
    }

    // GET : Lecture (Élément unique)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Champ findById(@PathVariable Long id) {
        return champS.findById(id);
    }

    // PUT : Remplacement
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Champ update(@PathVariable Long id, @Valid @RequestBody ChampDto form) {
        return champS.update(id, form);
    }

    // PATCH : Mise à Jour (partielle)
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Champ patch(@PathVariable Long id, @RequestBody ChampDto form) {
        // Note : @Valid retiré ici pour autoriser l'envoi d'un DTO incomplet lors d'une mise à jour partielle
        return champS.patch(id, form);
    }

    // DELETE : Suppression
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        champS.delete(id);
    }

    
}
