package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.EquipeDTO;
import tn.esprit.spring.kaddem.services.IEquipeService;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/equipe")

public class EquipeRestController {
	IEquipeService equipeService;
	// http://localhost:8089/Kaddem/equipe/retrieve-all-equipes
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/retrieve-all-equipes")
	public List<Equipe> getEquipes() {
		List<Equipe> listEquipes = equipeService.retrieveAllEquipes();
		return listEquipes;
	}
	// http://localhost:8089/Kaddem/equipe/retrieve-equipe/8
	@CrossOrigin(origins = "http://localhost:4200")

	@GetMapping("/retrieve-equipe/{equipe-id}")
	public Equipe retrieveEquipe(@PathVariable("equipe-id") Integer equipeId) {
		return equipeService.retrieveEquipe(equipeId);
	}



	// http://localhost:8089/Kaddem/equipe/remove-equipe/1
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/remove-equipe/{equipe-id}")
	public ResponseEntity<String> removeEquipe(@PathVariable("equipe-id") Integer equipeId) {
		// Logique de suppression
		equipeService.deleteEquipe(equipeId);
		return new ResponseEntity<>("Équipe supprimée avec succès", HttpStatus.OK);
	}

	// http://localhost:8089/Kaddem/equipe/update-equipe
	@CrossOrigin(origins = "http://localhost:4200")

	@PostMapping("/add-equipe")
	public Equipe addEquipe(@RequestBody EquipeDTO equipeDTO) {
		// Create a new instance of Equipe using data from EquipeDTO
		Equipe equipe = new Equipe();
		equipe.setNomEquipe(equipeDTO.getNomEquipe());
		equipe.setNiveau(equipeDTO.getNiveau());

		// Set other attributes based on EquipeDTO or any default values as needed
		equipe.setEtudiants(equipeDTO.getEtudiants());
		equipe.setDetailEquipe(equipeDTO.getDetailEquipe());

		// Set other attributes accordingly

		// Call the service to add the equipe
		return equipeService.addEquipe(equipe);
	}


	@PutMapping("/update-equipe")
	public Equipe updateEquipe(@RequestBody EquipeDTO equipeDTO) {
		Equipe existingEquipe = equipeService.retrieveEquipe(equipeDTO.getIdEquipe());
		if (existingEquipe == null) {
			return null; // Handle the case where the equipe does not exist
		}

		// Update the properties of the existing equipe with those of EquipeDTO
		existingEquipe.setNomEquipe(equipeDTO.getNomEquipe());
		existingEquipe.setNiveau(equipeDTO.getNiveau());

		// Update other attributes based on EquipeDTO or any default values as needed
		existingEquipe.setEtudiants(equipeDTO.getEtudiants());
		existingEquipe.setDetailEquipe(equipeDTO.getDetailEquipe());

		// Update other attributes accordingly

		// Call the service to update the equipe
		return equipeService.updateEquipe(existingEquipe);
	}


	@Scheduled(cron="0 0 13 * * *")
	@PutMapping("/faireEvoluerEquipes")
	public void faireEvoluerEquipes() {
		 equipeService.evoluerEquipes() ;
	}
}


