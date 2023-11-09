package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.services.IDepartementService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartementServiceTestWithoutMock {

    @Autowired
    private IDepartementService departementService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testRetrieveAllDepartements() {
        List<Departement> departements = departementService.retrieveAllDepartements();
        assertNotNull(departements);
        assertFalse(departements.isEmpty());
    }

    @Test
    public void testAddDepartement() {
        Departement departement = new Departement("Sample Department");
        Departement addedDepartement = departementService.addDepartement(departement);
        assertNotNull(addedDepartement);
        assertNotNull(addedDepartement.getIdDepart());
        assertEquals("Sample Department", addedDepartement.getNomDepart());
    }

    @Test
    public void testUpdateDepartement() {
        Departement departement = new Departement("Sample Department");
        Departement addedDepartement = departementService.addDepartement(departement);

        addedDepartement.setNomDepart("Updated Department Name");

        Departement updatedDepartement = departementService.updateDepartement(addedDepartement);

        assertNotNull(updatedDepartement);
        assertEquals("Updated Department Name", updatedDepartement.getNomDepart());
    }

    @Test
    public void testRetrieveDepartement() {
        Departement departement = new Departement("Sample Department");
        Departement addedDepartement = departementService.addDepartement(departement);

        Departement retrievedDepartement = departementService.retrieveDepartement(addedDepartement.getIdDepart());
        assertNotNull(retrievedDepartement);
        assertEquals("Sample Department", retrievedDepartement.getNomDepart());
    }

    @Test
    public void testDeleteDepartement() {
        Departement departement = new Departement("Sample Department");
        Departement addedDepartement = departementService.addDepartement(departement);

        long departementId = addedDepartement.getIdDepart();
        departementService.deleteDepartement(departementId);

        Departement deletedDepartement = departementService.retrieveDepartement(departementId);
        assertNull(deletedDepartement);
    }
}
