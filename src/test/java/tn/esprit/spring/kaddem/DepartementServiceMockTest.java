package tn.esprit.spring.kaddem;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.*;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DepartementServiceMockTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementServiceImpl departementService;
    @Autowired
    private EtudiantServiceImpl etudiantService;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private ContratRepository contratRepository;
    @Autowired
    private EquipeRepository equipeRepository;


    @Before
    void setUp() {

        departementService = new DepartementServiceImpl(etudiantRepository, contratRepository, equipeRepository, departementRepository);
    }



    @Test
    public void testRetrieveAllDepartements() {
        List<Departement> departements = new ArrayList<>();
        departements.add(new Departement(1L, "Computer Science"));
        departements.add(new Departement(2L, "Electrical Engineering"));

        when(departementRepository.findAll()).thenReturn(departements);

        List<Departement> result = departementService.retrieveAllDepartements();

        assertEquals(2, result.size());
        assertEquals("Computer Science", result.get(0).getNomDepart());
        assertEquals("Electrical Engineering", result.get(1).getNomDepart());
    }

    @Test
    public void testAddDepartement() {
        Departement departement = new Departement("Mechanical Engineering");

        when(departementRepository.save(Mockito.any(Departement.class))).thenReturn(departement);

        Departement addedDepartement = departementService.addDepartement(departement);

        assertEquals("Mechanical Engineering", addedDepartement.getNomDepart());
    }

    @Test
    public void testUpdateDepartement() {
        Departement departement = new Departement(3L, "Computer Science");

        when(departementRepository.save(Mockito.any(Departement.class))).thenReturn(departement);

        Departement updatedDepartement = departementService.updateDepartement(departement);

        assertEquals("Computer Science", updatedDepartement.getNomDepart());
    }

    @Test
    public void testRetrieveDepartement() {
        Departement departement = new Departement(4L, "Computer Science");
        when(departementRepository.findById(4L)).thenReturn(java.util.Optional.of(departement));

        Departement retrievedDepartement = departementService.retrieveDepartement(4L);

        assertEquals("Computer Science", retrievedDepartement.getNomDepart());
    }

    @Test
    public void testRemoveDepartement() {
        Departement departement = new Departement(5L, "Mechanical Engineering");
        when(departementRepository.findById(5L)).thenReturn(java.util.Optional.of(departement));

        departementService.deleteDepartement(5L);

        assertNotNull(departement);
    }

    // Add more test methods as needed for your specific use cases.
}
