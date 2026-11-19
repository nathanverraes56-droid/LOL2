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
import com.basespringboot.service.ExternalApiService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/champ")
@RestController 
@RequiredArgsConstructor 
public class ChampController {
    private final ChampService champS;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
@SecurityRequirement(name = "bearerAuth")
    public Champ save(@Valid @RequestBody ChampDto champ){
     return champS.save(champ);
    }

    // GET: Read (List)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Champ> findAll() {
        return champS.findAll();
    }

    // GET: Read (Single element)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Champ findById(@PathVariable Long id) {
        return champS.findById(id);
    }

    // PUT: Full replacement
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
@SecurityRequirement(name = "bearerAuth")
    public Champ update(@PathVariable Long id, @Valid @RequestBody ChampDto form) {
        return champS.update(id, form);
    }

    // PATCH: Partial update
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
@SecurityRequirement(name = "bearerAuth")
    public Champ patch(@PathVariable Long id, @RequestBody ChampDto form) {
        return champS.patch(id, form);
    }

    // DELETE: Deletion
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
@SecurityRequirement(name = "bearerAuth")
    public void delete(@PathVariable Long id) {
        champS.delete(id);
    }

    // Ad in ChampController :
private final ExternalApiService externalApiService;

// Endpoint which uses the official API ofLeague of Legends (Data Dragon) 
@GetMapping("/external-lol")
public String getOfficialLolData() {
    String url = "https://ddragon.leagueoflegends.com/cdn/14.22.1/data/fr_FR/champion/Ahri.json";
    return externalApiService.getExternalData(url);
}
}