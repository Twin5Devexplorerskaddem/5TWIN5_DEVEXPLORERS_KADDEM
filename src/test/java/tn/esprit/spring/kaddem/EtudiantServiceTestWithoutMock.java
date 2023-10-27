package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.*;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EtudiantServiceTestWithoutMock {

    @Autowired
    private EtudiantServiceImpl etudiantService;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private ContratRepository contratRepository;
    @Autowired
    private EquipeRepository equipeRepository;
    @Autowired
    private DepartementRepository departementRepository;

    @BeforeEach
    public void setUp() {
        Departement departement = new Departement("Informatique");
        departementRepository.save(departement);

        Etudiant etudiant1 = new Etudiant("Alice", "Smith", Option.GAMIX);
        etudiant1.setDepartement(departement);
        etudiantRepository.save(etudiant1);

        Etudiant etudiant2 = new Etudiant("Bob", "Johnson", Option.SIM);
        etudiant2.setDepartement(departement);
        etudiantRepository.save(etudiant2);

        Contrat contrat = new Contrat(new Date(), new Date(), Specialite.IA, true, 1000);
        contrat.setEtudiant(etudiant1);
        contratRepository.save(contrat);

        Equipe equipe = new Equipe("Equipe 1", Niveau.EXPERT);
        equipe.getEtudiants().add(etudiant1);
        equipeRepository.save(equipe);
    }

    @Test
    public void testRetrieveAllEtudiants() {
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();
        assertFalse(etudiants.isEmpty());
        assertEquals(2, etudiants.size());
    }

    @Test
    public void testAddEtudiant() {
        Etudiant etudiant = new Etudiant("Charlie", "Brown", Option.SE);
        Etudiant addedEtudiant = etudiantService.addEtudiant(etudiant);
        assertNotNull(addedEtudiant);
        assertEquals("Charlie", addedEtudiant.getNomE());

        Etudiant retrievedEtudiant = etudiantRepository.findById(addedEtudiant.getIdEtudiant()).orElse(null);
        assertNotNull(retrievedEtudiant);
        assertEquals("Charlie", retrievedEtudiant.getNomE());
    }

    @Test
    public void testUpdateEtudiant() {
        Etudiant etudiant = etudiantRepository.findByNomEAndPrenomE("Alice", "Smith");
        etudiant.setNomE("Eve");
        Etudiant updatedEtudiant = etudiantService.updateEtudiant(etudiant);
        assertNotNull(updatedEtudiant);
        assertEquals("Eve", updatedEtudiant.getNomE());

        Etudiant retrievedEtudiant = etudiantRepository.findById(updatedEtudiant.getIdEtudiant()).orElse(null);
        assertNotNull(retrievedEtudiant);
        assertEquals("Eve", retrievedEtudiant.getNomE());
    }

    @Test
    public void testRetrieveEtudiant() {
        Etudiant etudiant = etudiantService.retrieveEtudiant(2);
        assertNotNull(etudiant);
        assertEquals("Bob", etudiant.getNomE());
    }

    @Test
    public void testRemoveEtudiant() {
        etudiantService.removeEtudiant(1);
        Etudiant etudiant = etudiantService.retrieveEtudiant(1);
        assertNull(etudiant);
    }

    @Test
    public void testAssignEtudiantToDepartement() {
        etudiantService.assignEtudiantToDepartement(2, 1);
        Etudiant etudiant = etudiantService.retrieveEtudiant(2);
        assertNotNull(etudiant.getDepartement());
        assertEquals("Informatique", etudiant.getDepartement().getNomDepart());
    }

    @Test
    public void testAddAndAssignEtudiantToEquipeAndContract() {
        Etudiant etudiant = new Etudiant("David", "Lee", Option.NIDS);
        etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, 1, 1);

        Contrat contrat = contratRepository.findByEtudiant(etudiant);
        assertNotNull(contrat);
        assertEquals(Specialite.IA, contrat.getSpecialite());

        Equipe equipe = equipeRepository.findById(1).orElse(null);
        assertNotNull(equipe);
        assertTrue(equipe.getEtudiants().contains(etudiant));
    }

    @Test
    public void testGetEtudiantsByDepartement() {
        List<Etudiant> etudiants = etudiantService.getEtudiantsByDepartement(1);
        assertFalse(etudiants.isEmpty());
        assertEquals(2, etudiants.size());
        assertEquals("Alice", etudiants.get(0).getNomE());
        assertEquals("Bob", etudiants.get(1).getNomE());
    }
}
