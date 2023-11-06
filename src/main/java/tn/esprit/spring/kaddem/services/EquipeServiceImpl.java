package tn.esprit.spring.kaddem.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.*;

@Slf4j
@AllArgsConstructor
@Service
public class EquipeServiceImpl implements IEquipeService{
	EquipeRepository equipeRepository;


	public List<Equipe> retrieveAllEquipes(){
	return  (List<Equipe>) equipeRepository.findAll();
	}
	public Equipe addEquipe(Equipe e){
		return (equipeRepository.save(e));
	}

	public  void deleteEquipe(Integer idEquipe){
		Equipe e=retrieveEquipe(idEquipe);
		equipeRepository.delete(e);
	}

	public Equipe retrieveEquipe(int equipeId) {
		Optional<Equipe> equipeOptional = equipeRepository.findById(equipeId);

		if (equipeOptional.isPresent()) {
			return equipeOptional.get();
		} else {
			// Gérer le cas où l'équipe n'a pas été trouvée, par exemple, en lançant une exception appropriée.
			throw new NoSuchElementException("L'équipe avec l'ID " + equipeId + " n'a pas été trouvée.");
		}
	}


	public Equipe updateEquipe(Equipe e){
	return (	equipeRepository.save(e));
	}

	@Override
	public Equipe retrieveEquipe(Integer equipeId) {
		Optional<Equipe> equipeOptional = equipeRepository.findById(equipeId);

		if (equipeOptional.isPresent()) {
			return equipeOptional.get();
		} else {
			// Gérer le cas où l'équipe n'a pas été trouvée, par exemple, en lançant une exception appropriée.
			throw new NoSuchElementException("L'équipe avec l'ID " + equipeId + " n'a pas été trouvée.");
		}
	}

	public void evoluerEquipes() {
		List<Equipe> equipes = (List<Equipe>) equipeRepository.findAll();

		for (Equipe equipe : equipes) {
			Niveau niveauEquipe = equipe.getNiveau();

			// Équipe JUNIOR ou SENIOR seulement
			if (niveauEquipe == Niveau.JUNIOR || niveauEquipe == Niveau.SENIOR) {
				int nbEtudiantsAvecContratsActifs = 0;

				for (Etudiant etudiant : equipe.getEtudiants()) {
					if (nbEtudiantsAvecContratsActifs >= 3) {
						break;
					}

					for (Contrat contrat : etudiant.getContrats()) {
						try {
							// Obtenez la date actuelle
							Date dateSysteme = new Date();

							// Calculez la différence en années
							long difference_In_Time = dateSysteme.getTime() - contrat.getDateFinContrat().getTime();
							long difference_In_Years = difference_In_Time / (1000L * 60 * 60 * 24 * 365);

							if (!contrat.getArchive() && difference_In_Years > 1) {
								nbEtudiantsAvecContratsActifs++;
								break;
							}
						} catch (NullPointerException | ArithmeticException ex) {
							// Gérer les exceptions (par exemple, journalisation)
						}
					}
				}

				if (nbEtudiantsAvecContratsActifs >= 3) {
					// Mettez à jour le niveau de l'équipe en conséquence
					switch (niveauEquipe) {
						case JUNIOR:
							equipe.setNiveau(Niveau.SENIOR);
							break;
						case SENIOR:
							equipe.setNiveau(Niveau.EXPERT);
							break;
						// Ajoutez d'autres cas si nécessaire
					}
					equipeRepository.save(equipe);
				}
			}
		}
	}



}