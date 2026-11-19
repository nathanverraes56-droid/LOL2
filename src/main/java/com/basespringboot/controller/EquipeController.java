package com.basespringboot.controller;

import com.basespringboot.dto.EquipeDto;
import com.basespringboot.entity.Equipe;
import com.basespringboot.service.EquipeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipe")
@RequiredArgsConstructor
public class EquipeController {

    private final EquipeService equipeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "bearerAuth")
    public Equipe save(@Valid @RequestBody EquipeDto equipeDto) {
        return equipeService.save(equipeDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Equipe> findAll() {
        return equipeService.findAll();
    }
}