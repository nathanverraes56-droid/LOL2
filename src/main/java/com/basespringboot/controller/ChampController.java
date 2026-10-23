package com.basespringboot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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
    
}
