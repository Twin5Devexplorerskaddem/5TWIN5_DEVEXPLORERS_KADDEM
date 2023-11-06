package tn.esprit.spring.kaddem.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.*;
import tn.esprit.spring.kaddem.repositories.DetailEquipeRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

    @Slf4j
    @AllArgsConstructor
    @Service
    public class DetailEquipeServiceImpl implements IDetailEquipe{

        DetailEquipeRepository d;
        private static final Logger logger = LogManager.getLogger(DetailEquipeServiceImpl.class);


//        @Override
//        public List<DetailEquipe> retrieveAllDetailEquipes() {
//            return (List<DetailEquipe>) d.findAll();
//        }

        @Override
        public List<DetailEquipe> retrieveAllDetailEquipes() {
            List<DetailEquipe> detailEquipes = (List<DetailEquipe>) d.findAll();

            if (detailEquipes != null && !detailEquipes.isEmpty()) {
                logger.info("Liste des DetailEquipes récupérée avec succès : {} éléments", detailEquipes.size());
            } else {
                logger.warn("Aucun DetailEquipe trouvé dans la base de données.");
            }

            return detailEquipes;
        }



      //  @Override
//        public DetailEquipe addEquipe(DetailEquipe e) {
//
//            log.info("add Detailequipe");
//            return (d.save(e));
//        }
//        @Override
//        public DetailEquipe addEquipe(DetailEquipe e) {
//            logger.info("Adding a new DetailEquipe");
//            try {
//                DetailEquipe savedEquipe = d.save(e);
//                logger.info("DetailEquipe added successfully: " + savedEquipe);
//                return savedEquipe;
//            } catch (Exception ex) {
//                logger.error("Error adding DetailEquipe: " + ex.getMessage(), ex);
//                throw ex; // Vous pouvez personnaliser le traitement des erreurs ici
//            }
//        }

        @Override
        public DetailEquipe addEquipe(DetailEquipe e) {
            logger.info("Tentative d'ajout de DetailEquipe : {}", e);

            DetailEquipe equipe = d.save(e);

            if (equipe != null) {
                logger.info("DetailEquipe ajouté avec succès : {}", equipe);
            } else {
                logger.error("Échec de l'ajout de DetailEquipe");
            }

            logger.debug("Ceci est un message de débogage : {}", equipe);

            return equipe;
        }


//        @Override
//        public void deleteEquipe(Integer idEquipe) {
//            DetailEquipe e=retrieveEquipe(idEquipe);
//            log.info("delete equipe");
//            d.delete(e);
//
//        }

        @Override
        public void deleteEquipe(Integer idEquipe) {
            DetailEquipe e = retrieveEquipe(idEquipe);
            if (e != null) {
                logger.info("Tentative de suppression de DetailEquipe : {}", e);
                d.delete(e);
                logger.info("DetailEquipe supprimé avec succès : {}", e);
            } else {
                logger.error("Échec de la suppression de DetailEquipe : aucun DetailEquipe trouvé avec l'ID : {}", idEquipe);
            }
        }


//        @Override
//        public DetailEquipe updateEquipe(DetailEquipe e) {
//            log.info("update Detailequipe");
//            return (d.save(e));
//        }

//        @Override
//        public DetailEquipe updateEquipe(DetailEquipe updatedEquipe) {
//
//            DetailEquipe existingEquipe = d.findById(updatedEquipe.getIdDetailEquipe())
//                    .orElseThrow(() -> new EntityNotFoundException("Equipe not found"));
//
//            // Update the fields of the existing entity
//            existingEquipe.setSalle(updatedEquipe.getSalle());
//            existingEquipe.setThematique(updatedEquipe.getThematique());
//
//            // Save the updated entity
//            log.info("update Detailequipe");
//            return d.save(existingEquipe);
//        }

        @Override
        public DetailEquipe updateEquipe(DetailEquipe updatedEquipe) {
            DetailEquipe existingEquipe = d.findById(updatedEquipe.getIdDetailEquipe())
                    .orElseThrow(() -> new EntityNotFoundException("Equipe not found"));

            // Update the fields of the existing entity
            existingEquipe.setSalle(updatedEquipe.getSalle());
            existingEquipe.setThematique(updatedEquipe.getThematique());

            // Save the updated entity
            DetailEquipe savedEquipe = d.save(existingEquipe);

            if (savedEquipe != null) {
                logger.info("DetailEquipe mis à jour avec succès : {}", savedEquipe);
            } else {
                logger.error("Échec de la mise à jour de DetailEquipe : {}", updatedEquipe);
            }

            return savedEquipe;
        }



        @Override
        public DetailEquipe retrieveEquipe(Integer equipeId) {
            Optional<DetailEquipe> optionalDetailEquipe = d.findById(equipeId);
            return optionalDetailEquipe.orElse(new DetailEquipe()); // Provide a default value if the optional is empty
        }
        public void evoluerEquipes(){
//            List<DetailEquipe> equipes = (List<DetailEquipe>) d.findAll();
//            for (DetailEquipe equipe : equipes) {
//                if ((equipe.getSalle().equals(Niveau.JUNIOR)) || (equipe.getSalle().equals(Niveau.SENIOR))) {
//                    List<Equipe> etudiants = (List<Equipe>) equipe.getEquipe();
//                    Integer nbEtudiantsAvecContratsActifs=0;
//                    for (Equipe etudiant : etudiants) {
//                        Set<Contrat> contrats = etudiant.getContrats();
//                        //Set<Contrat> contratsActifs=null;
//                        for (Contrat contrat : contrats) {
//                            Date dateSysteme = new Date();
//                            long difference_In_Time = dateSysteme.getTime() - contrat.getDateFinContrat().getTime();
//                            long difference_In_Years = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));
//                            if ((contrat.getArchive() == false) && (difference_In_Years > 1)) {
//                                //	contratsActifs.add(contrat);
//                                nbEtudiantsAvecContratsActifs++;
//                                break;
//                            }
//                            if (nbEtudiantsAvecContratsActifs >= 3) break;
//                        }
//                    }
//                    if (nbEtudiantsAvecContratsActifs >= 3){
//                        if (equipe.getNiveau().equals(Niveau.JUNIOR)){
//                            equipe.setNiveau(Niveau.SENIOR);
//                            equipeRepository.save(equipe);
//                            break;
//                        }
//                        if (equipe.getNiveau().equals(Niveau.SENIOR)){
//                            equipe.setNiveau(Niveau.EXPERT);
//                            equipeRepository.save(equipe);
//                            break;
//                        }
//                    }
//                }
//
//            }

        }

    }
