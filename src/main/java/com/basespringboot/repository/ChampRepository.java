package com.basespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basespringboot.entity.Champ;

@Repository 
public interface ChampRepository extends JpaRepository<Champ, Long> {

    
} 
