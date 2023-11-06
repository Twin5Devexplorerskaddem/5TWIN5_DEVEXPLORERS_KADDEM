package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Universite;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UniversiteServiceImplTestwithoutMock {
    @Autowired
    private IUniversiteService universiteService;

    @BeforeEach
    public void setUp() {
        // Add setup logic for the Universite entity if needed.
    }

    @Test
    public void testRetrieveAllUniversites() {
        List<Universite> universites = universiteService.retrieveAllUniversites();
        assertNotNull(universites);
        assertFalse(universites.isEmpty());
        // Add assertions as needed for the retrieved Universite entities.
    }

    @Test
    public void testAddUniversite() {
        Universite universite = new Universite("Sample University");
        Universite addedUniversite = universiteService.addUniversite(universite);
        assertNotNull(addedUniversite);
        assertNotNull(addedUniversite.getIdUniv());
        assertEquals("Sample University", addedUniversite.getNomUniv());

        // You can also retrieve the added university from the service/repository and perform assertions.
    }

    @Test
    public void testUpdateUniversite() {
        // Add a Universite entity, update it, and then assert the changes.
        Universite universite = new Universite("Sample University");
        Universite addedUniversite = universiteService.addUniversite(universite);

        addedUniversite.setNomUniv("Updated University Name");
        Universite updatedUniversite = universiteService.updateUniversite(addedUniversite);

        assertNotNull(updatedUniversite);
        assertEquals("Updated University Name", updatedUniversite.getNomUniv());

    }

    @Test
    public void testRetrieveUniversite() {
        // Add a Universite entity, retrieve it by ID, and assert its attributes.
        Universite universite = new Universite("Sample University");
        Universite addedUniversite = universiteService.addUniversite(universite);

        Universite retrievedUniversite = universiteService.retrieveUniversite(addedUniversite.getIdUniv());
        assertNotNull(retrievedUniversite);
        assertEquals("Sample University", retrievedUniversite.getNomUniv());
    }

    @Test
    public void testDeleteUniversite() {
        // Add a Universite entity, delete it, and then verify it's no longer in the database.
        Universite universite = new Universite("Sample University");
        Universite addedUniversite = universiteService.addUniversite(universite);

        int universiteId = addedUniversite.getIdUniv();
        universiteService.deleteUniversite(universiteId);

        Universite deletedUniversite = universiteService.retrieveUniversite(universiteId);
        assertNull(deletedUniversite);
    }

}