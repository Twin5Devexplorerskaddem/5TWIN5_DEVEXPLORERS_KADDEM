package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.services.IDetailEquipe;
import tn.esprit.spring.kaddem.services.IEquipeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Detailequipe")
public class DetailEquipeController {
    IDetailEquipe equipeService;
    // http://localhost:8089/Kaddem/Detailequipe/retrieve-all-equipes
    @GetMapping("/retrieve-all-detailequipes")
    public List<DetailEquipe> getEquipes() {
        List<DetailEquipe> listEquipes = equipeService.retrieveAllDetailEquipes();
        return listEquipes;
    }
    //7
    // http://localhost:8089/Kaddem/Detailequipe/retrieve-equipe/8
    @GetMapping(value = "/retrieve-detailequipe/{equipe-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DetailEquipe retrieveEquipe(@PathVariable("equipe-id") Integer equipeId) {
        return equipeService.retrieveEquipe(equipeId);
    }

    // http://localhost:8089/Kaddem/Detailequipe/add-equipe
    @PostMapping("/add-detailequipe")
    public DetailEquipe addEquipe(@RequestBody DetailEquipe e) {
        DetailEquipe equipe = equipeService.addEquipe(e);
        return equipe;
    }
//ufhgvjygyug
    // http://localhost:8089/Kaddem/Detailequipe/remove-equipe/1
    @DeleteMapping("/remove-detailequipe/{idDetailEquipe}")
    public void removeEquipe(@PathVariable("idDetailEquipe") Integer equipeId) {
        equipeService.deleteEquipe(equipeId);
    }

    // http://localhost:8089/Kaddem/Detailequipe/update-equipe
//    @PutMapping("/update-detailequipe")
//    public DetailEquipe updateEtudiant(@RequestBody DetailEquipe e) {
//        DetailEquipe equipe= equipeService.updateEquipe(e);
//        return equipe;
//    }

    @PutMapping("/update-detailequipe")
    public DetailEquipe updateEtudiant(@RequestBody DetailEquipe updatedEquipe) {
        DetailEquipe equipe = equipeService.updateEquipe(updatedEquipe);
        return equipe;
    }

    @Scheduled(cron="0 0 13 * * *")
    @PutMapping("/faireEvoluerEquipes")
    public void faireEvoluerEquipes() {
        equipeService.evoluerEquipes() ;
    }
}
