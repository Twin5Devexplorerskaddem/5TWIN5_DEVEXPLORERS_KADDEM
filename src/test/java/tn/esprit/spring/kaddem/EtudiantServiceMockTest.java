package tn.esprit.spring.kaddem;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.*;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

import java.util.*;

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
    @Mock
    private Contrat contrat; // Create a mock Contrat object
    @Mock
    private Equipe equipe; // Create a mock Equipe object

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Before
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
public void testGetEtudiantsByDepartement() {

    Integer idDepartement = 1;


    List<Etudiant> etudiants = new ArrayList<>();
    etudiants.add(new Etudiant(1, "John", "Doe", Option.SE));
    etudiants.add(new Etudiant(2, "Alice", "Johnson", Option.SIM));


    Mockito.when(etudiantRepository.findEtudiantsByDepartement_IdDepart(idDepartement))
            .thenReturn(etudiants);


    List<Etudiant> result = etudiantService.getEtudiantsByDepartement(idDepartement);


    assertFalse(result.isEmpty());
    assertEquals(2, result.size());
    assertEquals("John", result.get(0).getNomE());
    assertEquals("Alice", result.get(1).getNomE());
}
//    @Test
//    public void testAddAndAssignEtudiantToEquipeAndContract() {
//        // Arrange
//        Etudiant etudiant = new Etudiant();
//        Integer idContrat = 1;
//        Integer idEquipe = 2;
//
//        Contrat contrat = new Contrat();
//        Equipe equipe = new Equipe();
//
//        when(contratRepository.findById(idContrat)).thenReturn(Optional.of(contrat));
//        when(equipeRepository.findById(idEquipe)).thenReturn(Optional.of(equipe));
//
//        // Act
//        Etudiant result = etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, idContrat, idEquipe);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(etudiant, contrat.getEtudiant());
//        assertTrue(equipe.getEtudiants().contains(etudiant));
//    }



}
