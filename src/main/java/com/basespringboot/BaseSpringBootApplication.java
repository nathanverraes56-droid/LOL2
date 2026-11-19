package com.basespringboot;

import com.basespringboot.entity.Champ;
import com.basespringboot.entity.Equipe;
import com.basespringboot.repository.ChampRepository;
import com.basespringboot.repository.EquipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BaseSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseSpringBootApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(EquipeRepository equipeRepository, ChampRepository champRepository) {
        return args -> {
            // Vérifie si la base est vide pour éviter les doublons si vous passez en ddl-auto: update plus tard
            if (equipeRepository.count() == 0 && champRepository.count() == 0) {
                
                // 1. Création et sauvegarde de l'équipe
                Equipe t1 = new Equipe();
                t1.setNom("T1");
                equipeRepository.save(t1);

                // 2. Création des 5 champions liés à l'équipe
                Champ garen = new Champ();
                garen.setNom("Garen");
                garen.setSexe("Homme");
                garen.setRole("Combattant");
                garen.setEspece("Humain");
                garen.setRessource("Aucune");
                garen.setPortee("Mêlée");
                garen.setRegion("Demacia");
                garen.setAnnee(2010);
                garen.setEquipe(t1);

                Champ leeSin = new Champ();
                leeSin.setNom("Lee Sin");
                leeSin.setSexe("Homme");
                leeSin.setRole("Assassin");
                leeSin.setEspece("Humain");
                leeSin.setRessource("Énergie");
                leeSin.setPortee("Mêlée");
                leeSin.setRegion("Ionia");
                leeSin.setAnnee(2011);
                leeSin.setEquipe(t1);

                Champ ahri = new Champ();
                ahri.setNom("Ahri");
                ahri.setSexe("Femme");
                ahri.setRole("Mage");
                ahri.setEspece("Vastaya");
                ahri.setRessource("Mana");
                ahri.setPortee("Distance");
                ahri.setRegion("Ionia");
                ahri.setAnnee(2011);
                ahri.setEquipe(t1);

                Champ jinx = new Champ();
                jinx.setNom("Jinx");
                jinx.setSexe("Femme");
                jinx.setRole("Tireur");
                jinx.setEspece("Humain");
                jinx.setRessource("Mana");
                jinx.setPortee("Distance");
                jinx.setRegion("Zaun");
                jinx.setAnnee(2013);
                jinx.setEquipe(t1);

                Champ thresh = new Champ();
                thresh.setNom("Thresh");
                thresh.setSexe("Homme");
                thresh.setRole("Support");
                thresh.setEspece("Spectre");
                thresh.setRessource("Mana");
                thresh.setPortee("Distance");
                thresh.setRegion("Îles Obscures");
                thresh.setAnnee(2013);
                thresh.setEquipe(t1);

                // 3. Sauvegarde groupée en base de données
                champRepository.saveAll(List.of(garen, leeSin, ahri, jinx, thresh));

                System.out.println("Base de données initialisée avec l'équipe T1 et ses 5 champions.");
            }
        };
    }
}