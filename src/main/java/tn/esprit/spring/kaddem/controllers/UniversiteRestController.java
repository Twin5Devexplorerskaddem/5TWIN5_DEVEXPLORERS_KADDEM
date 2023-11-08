package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.services.IUniversiteService;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/universite")
public class UniversiteRestController {

	IUniversiteService universiteService;
	// http://localhost:8089/Kaddem/universite/retrieve-all-universites
	@CrossOrigin(origins = "*")
	@GetMapping("/retrieve-all-universites")
	public List<Universite> getUniversites() {
		return universiteService.retrieveAllUniversites();
	}
	// http://localhost:8089/Kaddem/universite/retrieve-universite/8
	@CrossOrigin(origins = "*")
	@GetMapping("/retrieve-universite/{universite-id}")
	public Universite retrieveUniversite(@PathVariable("universite-id") Integer universiteId) {
		return universiteService.retrieveUniversite(universiteId);
	}
	// http://localhost:8089/Kaddem/universite/add-universite
	@CrossOrigin(origins = "*")
	@PostMapping("/add-universite")
	public Universite addUniversite(@RequestBody Universite universite) {
		return  universiteService.addUniversite(universite);

	}

	// http://localhost:8089/Kaddem/universite/remove-universite/1
	@CrossOrigin(origins = "*")
	@DeleteMapping("/remove-universite/{universite-id}")
	public void removeUniversite(@PathVariable("universite-id") Integer universiteId) {
		universiteService.deleteUniversite(universiteId);
	}

	// http://localhost:8089/Kaddem/universite/update-universite
	@CrossOrigin(origins = "*")
	@PutMapping("/update-universite")
	public Universite updateUniversite(@RequestBody Universite universite) {
		return  universiteService.updateUniversite(universite);

	}

}


