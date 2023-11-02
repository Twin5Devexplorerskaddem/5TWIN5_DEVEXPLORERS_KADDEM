package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UniversiteServiceMockTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @BeforeEach
    void setUp() {
        universiteService = new UniversiteServiceImpl(universiteRepository, departementRepository);
    }

    @Test
    public void testRetrieveAllUniversites() {
        List<Universite> universites = new ArrayList<>();
        universites.add(new Universite(1, "Harvard"));
        universites.add(new Universite(2, "MIT"));

        when(universiteRepository.findAll()).thenReturn(universites);

        List<Universite> result = universiteService.retrieveAllUniversites();

        assertEquals(2, result.size());
        assertEquals("Harvard", result.get(0).getNomUniv());
        assertEquals("MIT", result.get(1).getNomUniv());
    }

    @Test
    public void testAddUniversite() {
        Universite universite = new Universite("Stanford");

        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        Universite addedUniversite = universiteService.addUniversite(universite);

        assertEquals("Stanford", addedUniversite.getNomUniv());
    }

    @Test
    public void testUpdateUniversite() {
        Universite universite = new Universite(3, "Oxford");

        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        Universite updatedUniversite = universiteService.updateUniversite(universite);

        assertEquals("Oxford", updatedUniversite.getNomUniv());
    }

    @Test
    public void testRetrieveUniversite() {
        Universite universite = new Universite(4, "Cambridge");
        when(universiteRepository.findById(4)).thenReturn(Optional.of(universite));

        Universite retrievedUniversite = universiteService.retrieveUniversite(4);

        assertEquals("Cambridge", retrievedUniversite.getNomUniv());
    }

    @Test
    public void testDeleteUniversite() {
        Universite universite = new Universite(5, "Yale");
        when(universiteRepository.findById(5)).thenReturn(Optional.of(universite));

        universiteService.deleteUniversite(5);

        verify(universiteRepository, times(1)).delete(universite);
    }

    @Test
    public void testAssignUniversiteToDepartement() {
        Universite universite = new Universite(6, "Princeton");
        Departement departement = new Departement(1, "Computer Science");
        when(universiteRepository.findById(6)).thenReturn(Optional.of(universite));
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        universiteService.assignUniversiteToDepartement(6, 1);

        Set<Departement> universiteDepartements = universite.getDepartements();
        assertTrue(universiteDepartements.contains(departement));
    }

    @Test
    public void testRetrieveDepartementsByUniversite() {
        Universite universite = new Universite(7, "Columbia");
        Set<Departement> departements = new HashSet<>();
        departements.add(new Departement(2, "Physics"));
        departements.add(new Departement(3, "Chemistry"));
        universite.setDepartements(departements);

        when(universiteRepository.findById(7)).thenReturn(Optional.of(universite));

        Set<Departement> retrievedDepartements = universiteService.retrieveDepartementsByUniversite(7);

        assertEquals(2, retrievedDepartements.size());
        assertTrue(retrievedDepartements.stream().anyMatch(departement -> departement.getNomDepart().equals("Physics")));
        assertTrue(retrievedDepartements.stream().anyMatch(departement -> departement.getNomDepart().equals("Chemistry")));
    }
}
