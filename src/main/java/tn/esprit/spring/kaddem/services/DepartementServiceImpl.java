package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.List;

@Slf4j

@Service
public class DepartementServiceImpl implements IDepartementService{
	@Autowired
	DepartementRepository departementRepository;


	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	ContratRepository contratRepository;
	@Autowired
	EquipeRepository equipeRepository;

	public DepartementServiceImpl(EtudiantRepository etudiantRepository, ContratRepository contratRepository, EquipeRepository equipeRepository, DepartementRepository departementRepository) {
		this.etudiantRepository = etudiantRepository;
		this.contratRepository = contratRepository;
		this.equipeRepository = equipeRepository;
		this.departementRepository = departementRepository;
	}
	public List<Departement> retrieveAllDepartements(){
		return (List<Departement>) departementRepository.findAll();
	}

	public Departement addDepartement (Departement d){
		return departementRepository.save(d);
	}

	public   Departement updateDepartement (Departement d){
		return departementRepository.save(d);
	}

	public  Departement retrieveDepartement (Long idDepart){
		return departementRepository.findById(idDepart).get();
	}
	public  void deleteDepartement(Long idDepartement){
		Departement d=retrieveDepartement(idDepartement);
		departementRepository.delete(d);
	}

	@Override
	public List<Etudiant> getEtudiantsByDepar(Long idDepartement) {
		return null;
	}


}
