//package tn.esprit.spring.kaddem;
//
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
//import tn.esprit.spring.kaddem.entities.Contrat;
//import tn.esprit.spring.kaddem.entities.Etudiant;
//import tn.esprit.spring.kaddem.entities.Option;
//import tn.esprit.spring.kaddem.entities.Specialite;
//import tn.esprit.spring.kaddem.repositories.ContratRepository;
//import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
//import tn.esprit.spring.kaddem.services.ContratServiceImpl;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@SpringBootTest
//@SpringJUnitConfig
//public class ContratServiceImplWithoutMocksTest {
//@Autowired
//    private ContratServiceImpl contratService;
//    @Autowired
//    private ContratRepository contratRepository;
//   @Autowired
//    private EtudiantRepository etudiantRepository;
//
//    @BeforeEach
//    public void setup() {
//        contratRepository.deleteAll();
//
//        Date dateSysteme = new Date();
//
//        // Contract ending in 15 days
//        Contrat contrat15Days = new Contrat();
//        contrat15Days.setDateDebutContrat(new Date(dateSysteme.getTime() - (14 * 24 * 60 * 60 * 1000))); // 14 days ago
//        contrat15Days.setDateFinContrat(dateSysteme);
//        contrat15Days.setSpecialite(Specialite.IA);
//        contrat15Days.setArchive(false);
//        contratRepository.save(contrat15Days);
//
//        // Contract to be archived (expired)
//        Contrat contractToArchive = new Contrat();
//        contractToArchive.setDateDebutContrat(new Date(dateSysteme.getTime() - (30 * 24 * 60 * 60 * 1000))); // 30 days ago
//        contractToArchive.setDateFinContrat(dateSysteme);
//        contractToArchive.setSpecialite(Specialite.IA);
//        contractToArchive.setArchive(false);
//        contratRepository.save(contractToArchive);
//
//        // Contract not to be archived (still active)
//        Contrat contractNotToArchive = new Contrat();
//        contractNotToArchive.setDateDebutContrat(new Date(dateSysteme.getTime() - (10 * 24 * 60 * 60 * 1000))); // 10 days ago
//        contractNotToArchive.setDateFinContrat(dateSysteme);
//        contractNotToArchive.setSpecialite(Specialite.IA);
//        contractNotToArchive.setArchive(false);
//        contratRepository.save(contractNotToArchive);
//    }
//
//    @Test
//    public void testRetrieveAllContratsWithoutMocks() {
//        // Save real data to the database or repository.
//        Contrat contrat1 = new Contrat();
//        contrat1.setIdContrat(1);
//        contrat1.setDateDebutContrat(new Date());
//        contrat1.setDateFinContrat(new Date());
//        contrat1.setSpecialite(Specialite.IA);
//        contrat1.setArchive(false);
//        contrat1.setMontantContrat(1000);
//        contrat1.setEtudiant(new Etudiant("omar","charfi", Option.GAMIX));
//        contratRepository.save(contrat1);
//
//        Contrat contrat2 = new Contrat();
//        contrat2.setIdContrat(2);
//        contrat2.setDateDebutContrat(new Date());
//        contrat2.setDateFinContrat(new Date());
//        contrat2.setSpecialite(Specialite.CLOUD);
//        contrat2.setArchive(false);
//        contrat2.setMontantContrat(1500);
//        contrat2.setEtudiant(new Etudiant("chedly","kchaou", Option.SE));
//        contratRepository.save(contrat2);
//        List<Contrat> contrats = contratService.retrieveAllContrats();
//        assertEquals(5, contrats.size());
//
//    }
//
//    @Test
//    public void testUpdateContrat() {
//
//        Contrat contrat = new Contrat();
//        contrat.setIdContrat(contrat.getIdContrat());
//        contrat.setMontantContrat(1000);
//
//
//        contratRepository.save(contrat);
//        Contrat updatedContrat = contratService.updateContrat(contrat);
//        Contrat retrievedContrat = contratRepository.findById(contrat.getIdContrat()).orElse(null);
//        assertEquals(contrat, updatedContrat);
//        assertEquals(contrat, retrievedContrat);
//
//    }
//
//    @Test
//    public void testAddContrat() {
//
//        Contrat contrat = new Contrat();
//        contrat.setIdContrat(contrat.getIdContrat());
//
//
//        Contrat addedContrat = contratService.addContrat(contrat);
//
//
//        Contrat retrievedContrat = contratRepository.findById(contrat.getIdContrat()).orElse(null);
//
//
//        assertEquals(contrat, addedContrat);
//        assertEquals(contrat, retrievedContrat);
//
//    }
//
//    @Test
//    public void testRetrieveContrat() {
//
//        Contrat contrat = new Contrat();
//        contrat.setIdContrat(contrat.getIdContrat());
//        contratRepository.save(contrat);
//
//
//        Contrat retrievedContrat = contratService.retrieveContrat(contrat.getIdContrat());
//
//
//        assertEquals(contrat, retrievedContrat);
//
//    }
//
//    @Test
//    public void testRemoveContrat() {
//
//        Contrat contrat = new Contrat();
//        contrat.setIdContrat(1);
//        contratRepository.save(contrat);
//
//        contratService.removeContrat(contrat.getIdContrat());
//
//
//        Contrat removedContrat = contratRepository.findById(contrat.getIdContrat()).orElse(null);
//        assertNull(removedContrat);
//
//    }
//    @Test
//    public void testNbContratsValides() {
//        Date startDate = new Date(2022,04,13);
//        Date endDate = new Date(2022,05,13);
//        int expectedCount = 0;
//        Integer result = contratService.nbContratsValides(startDate, endDate);
//        assertEquals(expectedCount, result);
//    }
//
//    @Test
//    public void testRetrieveAndUpdateStatusContrat() {
//
//        contratService.retrieveAndUpdateStatusContrat();
//
//        List<Contrat> contrats = contratRepository.findAll();
//
//
//        List<Contrat> contrats15j = new ArrayList<>();
//        for (Contrat contrat : contrats) {
//            Date dateSysteme = new Date();
//            long difference_In_Time = dateSysteme.getTime() - contrat.getDateFinContrat().getTime();
//            long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
//            if (difference_In_Days == 15) {
//                contrats15j.add(contrat);
//            }
//        }
//
//
//        for (Contrat contrat : contrats) {
//            if (contrat.getArchive()) {
//                assertTrue(contrat.getDateFinContrat().before(new Date()));
//            }
//        }
//
//
//        for (Contrat contrat : contrats) {
//            if (!contrat.getArchive()) {
//                assertTrue(contrat.getDateFinContrat().after(new Date()));
//            }
//        }
//        assertEquals(0, contrats15j.size());
//    }
//
//    @Test
//    public void testGetChiffreAffaireEntreDeuxDates() {
//
//        contratRepository.deleteAll();
//
//        // Create contracts for testing
//        Date dateSysteme = new Date();
//
//        // Contract 1
//        Contrat contrat1 = new Contrat();
//        contrat1.setDateDebutContrat(new Date(dateSysteme.getTime() - (30 * 24 * 60 * 60 * 1000))); // 30 days ago
//        contrat1.setDateFinContrat(dateSysteme);
//        contrat1.setSpecialite(Specialite.IA);
//        contrat1.setArchive(false);
//        contratRepository.save(contrat1);
//
//        // Contract 2
//        Contrat contrat2 = new Contrat();
//        contrat2.setDateDebutContrat(new Date(dateSysteme.getTime() - (60 * 24 * 60 * 60 * 1000))); // 60 days ago
//        contrat2.setDateFinContrat(dateSysteme);
//        contrat2.setSpecialite(Specialite.CLOUD);
//        contrat2.setArchive(false);
//        contratRepository.save(contrat2);
//
//        Date startDate = new Date();
//        Date endDate = new Date(startDate.getTime() - (15 * 24 * 60 * 60 * 1000));
//
//        float chiffreAffaire = contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);
//
//
//
//        assertEquals(-350.0, chiffreAffaire, 0.01);
//    }
//}
