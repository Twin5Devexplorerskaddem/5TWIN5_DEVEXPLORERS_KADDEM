package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.*;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EtudiantServiceMockTest {

    @Mock
    private EtudiantRepository etudiantRepository;
    @Mock
    private ContratRepository contratRepository;
    @Mock
    private EquipeRepository equipeRepository;
    @Mock
    private DepartementRepository departementRepository;

    private EtudiantServiceImpl etudiantService;

    @BeforeEach
    void setUp() {
        etudiantService = new EtudiantServiceImpl(etudiantRepository, contratRepository, equipeRepository, departementRepository);
    }

    @Test
    public void testRetrieveAllEtudiants() {
        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(new Etudiant(1, "John", "Doe", Option.SE));
        etudiants.add(new Etudiant(2, "Alice", "Johnson", Option.SIM));

        when(etudiantRepository.findAll()).thenReturn(etudiants);

        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getNomE());
        assertEquals("Alice", result.get(1).getNomE());
    }

    @Test
    public void testAddEtudiant() {
        Etudiant etudiant = new Etudiant("Bob", "Smith", Option.SIM);

        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        Etudiant addedEtudiant = etudiantService.addEtudiant(etudiant);

        assertEquals("Bob", addedEtudiant.getNomE());
    }

    @Test
    public void testUpdateEtudiant() {
        Etudiant etudiant = new Etudiant(3, "Eve", "Parker", Option.NIDS);

        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        Etudiant updatedEtudiant = etudiantService.updateEtudiant(etudiant);

        assertEquals("Eve", updatedEtudiant.getNomE());
    }

    @Test
    public void testRetrieveEtudiant() {
        Etudiant etudiant = new Etudiant(4, "Charlie", "Brown", Option.GAMIX);
        when(etudiantRepository.findById(4)).thenReturn(Optional.of(etudiant));

        Etudiant retrievedEtudiant = etudiantService.retrieveEtudiant(4);

        assertEquals("Charlie", retrievedEtudiant.getNomE());
    }

    @Test
    public void testRemoveEtudiant() {
        Etudiant etudiant = new Etudiant(5, "David", "Lee", Option.NIDS);
        when(etudiantRepository.findById(5)).thenReturn(Optional.of(etudiant));

        etudiantService.removeEtudiant(5);

        verify(etudiantRepository, times(1)).delete(etudiant);
    }

    @Test
    public void testAssignEtudiantToDepartement() {
        Etudiant etudiant = new Etudiant(6, "Frank", "Johnson", Option.NIDS);
        Departement departement = new Departement(1, "IT");
        when(etudiantRepository.findById(6)).thenReturn(Optional.of(etudiant));
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        etudiantService.assignEtudiantToDepartement(6, 1);

        assertEquals(departement, etudiant.getDepartement());
    }

    @Test
    public void testAddAndAssignEtudiantToEquipeAndContract() {
        Etudiant etudiant = new Etudiant(7, "Grace", "Smith", Option.GAMIX);
        Contrat contrat = new Contrat(1, new Date(), new Date(), Specialite.IA, true, 1000);
        Equipe equipe = new Equipe(1, "Equipe 1", Niveau.EXPERT);

        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));

        Etudiant result = etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, 1, 1);

        assertEquals(etudiant, contrat.getEtudiant());
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
