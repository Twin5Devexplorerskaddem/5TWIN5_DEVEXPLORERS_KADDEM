package tn.esprit.spring.kaddem.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class ContratServiceImpl implements IContratService{
	private static final Logger logger = LogManager.getLogger(ContratServiceImpl.class);
@Autowired
ContratRepository contratRepository;
@Autowired
	EtudiantRepository etudiantRepository;
	public List<Contrat> retrieveAllContrats() {
		logger.info("Retrieving all contrats.");
		return (List<Contrat>) contratRepository.findAll();
	}

	public Contrat updateContrat(Contrat ce) {
		logger.info("Updating contrat with ID: {}", ce.getIdContrat());
		return contratRepository.save(ce);
	}

	public Contrat addContrat(Contrat ce) {
		logger.info("Adding a new contrat.");
		return contratRepository.save(ce);
	}

	public Contrat retrieveContrat(Integer idContrat) {
		logger.info("Retrieving contrat with ID: {}", idContrat);
		return contratRepository.findById(idContrat).orElse(null);
	}

	public void removeContrat(Integer idContrat) {
		Contrat c = retrieveContrat(idContrat);
		if (c != null) {
			logger.info("Removing contrat with ID: {}", idContrat);
			contratRepository.delete(c);
		} else {
			logger.warn("Unable to find contrat with ID: {} for removal", idContrat);
		}
	}

	public Contrat affectContratToEtudiant(Integer idContrat, String nomE, String prenomE) {
		Etudiant e = etudiantRepository.findByNomEAndPrenomE(nomE, prenomE);
		Contrat ce = contratRepository.findByIdContrat(idContrat);
		Set<Contrat> contrats = e.getContrats();
		Integer nbContratssActifs = 0;
		if (contrats.size() != 0) {
			for (Contrat contrat : contrats) {
				if (contrat.getArchive() != null && !contrat.getArchive()) {
					nbContratssActifs++;
				}
			}
		}
		if (nbContratssActifs <= 4) {
			ce.setEtudiant(e);
			contratRepository.save(ce);
			logger.info("Assigning contrat with ID {} to etudiant {} {}.", idContrat, nomE, prenomE);
		}
		return ce;
	}

	public Integer nbContratsValides(Date startDate, Date endDate) {
		return contratRepository.getnbContratsValides(startDate, endDate);
	}

	public void retrieveAndUpdateStatusContrat() {
		List<Contrat> contrats = contratRepository.findAll();
		List<Contrat> contrats15j = new ArrayList<>();
		List<Contrat> contratsAarchiver = new ArrayList();
		for (Contrat contrat : contrats) {
			Date dateSysteme = new Date();
			if (!contrat.getArchive()) {
				long difference_In_Time = dateSysteme.getTime() - contrat.getDateFinContrat().getTime();
				long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
				if (difference_In_Days == 15) {
					contrats15j.add(contrat);
					logger.info("Contrat ending in 15 days: {}", contrat);
				}
				if (difference_In_Days == 0) {
					contratsAarchiver.add(contrat);
					contrat.setArchive(true);
					contratRepository.save(contrat);
					logger.info("Archiving contrat with ID: {}", contrat.getIdContrat());
				}
			}
		}
	}

	public float getChiffreAffaireEntreDeuxDates(Date startDate, Date endDate) {
		float difference_In_Time = endDate.getTime() - startDate.getTime();
		float difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
		float difference_In_months = difference_In_Days / 30;
		List<Contrat> contrats = contratRepository.findAll();
		float chiffreAffaireEntreDeuxDates = 0;
		for (Contrat contrat : contrats) {
			if (contrat.getSpecialite() == Specialite.IA) {
				chiffreAffaireEntreDeuxDates += (difference_In_months * 300);
			} else if (contrat.getSpecialite() == Specialite.CLOUD) {
				chiffreAffaireEntreDeuxDates += (difference_In_months * 400);
			} else if (contrat.getSpecialite() == Specialite.RESEAUX) {
				chiffreAffaireEntreDeuxDates += (difference_In_months * 350);
			} else {
				chiffreAffaireEntreDeuxDates += (difference_In_months * 450);
			}
		}
		logger.info("Chiffre d'affaires entre deux dates: {}", chiffreAffaireEntreDeuxDates);
		return chiffreAffaireEntreDeuxDates;
	}


}
