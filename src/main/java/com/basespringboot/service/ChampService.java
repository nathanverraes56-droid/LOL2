package com.basespringboot.service;

import com.basespringboot.dto.ChampDto;
import com.basespringboot.entity.Champ;

public interface ChampService {

    Champ save (ChampDto champ);
} 