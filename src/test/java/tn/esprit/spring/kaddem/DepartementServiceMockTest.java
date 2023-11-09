package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DepartementServiceMockTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllDepartements() {
        Departement departement1 = new Departement(1L, "Computer Science");
        Departement departement2 = new Departement(2L, "Electrical Engineering");

        List<Departement> departementList = new ArrayList<>();
        departementList.add(departement1);
        departementList.add(departement2);

        Mockito.when(departementRepository.findAll()).thenReturn(departementList);

        List<Departement> retrievedDepartements = departementService.retrieveAllDepartements();

        Mockito.verify(departementRepository).findAll();

        assertEquals(2, retrievedDepartements.size());
        assertEquals(1L, retrievedDepartements.get(0).getIdDepart());
        assertEquals("Computer Science", retrievedDepartements.get(0).getNomDepart());
        assertEquals(2L, retrievedDepartements.get(1).getIdDepart());
        assertEquals("Electrical Engineering", retrievedDepartements.get(1).getNomDepart());
    }

    @Test
    public void testAddDepartement() {
        Departement departement = new Departement("Mechanical Engineering");

        Mockito.when(departementRepository.save(Mockito.any(Departement.class))).thenReturn(departement);

        Departement addedDepartement = departementService.addDepartement(departement);

        Mockito.verify(departementRepository).save(departement);

        assertEquals("Mechanical Engineering", addedDepartement.getNomDepart());
    }

    @Test
    public void testUpdateDepartement() {
        Departement departement = new Departement(3L, "Computer Science");

        Mockito.when(departementRepository.save(Mockito.any(Departement.class))).thenReturn(departement);

        Departement updatedDepartement = departementService.updateDepartement(departement);

        Mockito.verify(departementRepository).save(departement);

        assertEquals("Computer Science", updatedDepartement.getNomDepart());
    }

    @Test
    public void testRetrieveDepartement() {
        Departement departement = new Departement(4L, "Computer Science");
        Mockito.when(departementRepository.findById(4L)).thenReturn(Optional.of(departement));

        Departement retrievedDepartement = departementService.retrieveDepartement(4L);

        Mockito.verify(departementRepository).findById(4L);

        assertEquals("Computer Science", retrievedDepartement.getNomDepart());
    }

    @Test
    public void testRemoveDepartement() {
        Departement departement = new Departement(5L, "Mechanical Engineering");
        Mockito.when(departementRepository.findById(5L)).thenReturn(Optional.of(departement));

        departementService.deleteDepartement(5L);

        Mockito.verify(departementRepository).delete(departement);

        assertNotNull(departement);
    }
}
