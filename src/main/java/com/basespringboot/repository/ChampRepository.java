package com.basespringboot.repository;

import org.springframework.stereotype.Repository;

@Repository 
public interface ChampRepository extends JpaRepository<Champ, Long> {

    
} 
