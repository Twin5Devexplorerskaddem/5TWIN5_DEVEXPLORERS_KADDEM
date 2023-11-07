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
    }

    @Test
    public void testRetrieveAllUniversites() {
        List<Universite> universites = universiteService.retrieveAllUniversites();
        assertNotNull(universites);
        assertFalse(universites.isEmpty());
    }

    @Test
    public void testAddUniversite() {
        Universite universite = new Universite("Sample University", "Location", "Description", "sample@email.com");
        Universite addedUniversite = universiteService.addUniversite(universite);
        assertNotNull(addedUniversite);
        assertNotNull(addedUniversite.getIdUniv());
        assertEquals("Sample University", addedUniversite.getNomUniv());
        assertEquals("Location", addedUniversite.getLocalisation());
        assertEquals("Description", addedUniversite.getDescription());
        assertEquals("sample@email.com", addedUniversite.getEmail());

    }

    @Test
    public void testUpdateUniversite() {
        Universite universite = new Universite("Sample University", "Location", "Description", "sample@email.com");
        Universite addedUniversite = universiteService.addUniversite(universite);

        addedUniversite.setNomUniv("Updated University Name");
        addedUniversite.setLocalisation("New Location");
        addedUniversite.setDescription("New Description");
        addedUniversite.setEmail("new@email.com");

        Universite updatedUniversite = universiteService.updateUniversite(addedUniversite);

        assertNotNull(updatedUniversite);
        assertEquals("Updated University Name", updatedUniversite.getNomUniv());
        assertEquals("New Location", updatedUniversite.getLocalisation());
        assertEquals("New Description", updatedUniversite.getDescription());
        assertEquals("new@email.com", updatedUniversite.getEmail());
    }

    @Test
    public void testRetrieveUniversite() {
        Universite universite = new Universite("Sample University", "Location", "Description", "sample@email.com");
        Universite addedUniversite = universiteService.addUniversite(universite);

        Universite retrievedUniversite = universiteService.retrieveUniversite(addedUniversite.getIdUniv());
        assertNotNull(retrievedUniversite);
        assertEquals("Sample University", retrievedUniversite.getNomUniv());
        assertEquals("Location", retrievedUniversite.getLocalisation());
        assertEquals("Description", retrievedUniversite.getDescription());
        assertEquals("sample@email.com", retrievedUniversite.getEmail());
    }

    @Test
    public void testDeleteUniversite() {
        Universite universite = new Universite("Sample University", "Location", "Description", "sample@email.com");
        Universite addedUniversite = universiteService.addUniversite(universite);

        int universiteId = addedUniversite.getIdUniv();
        universiteService.deleteUniversite(universiteId);

        Universite deletedUniversite = universiteService.retrieveUniversite(universiteId);
        assertNull(deletedUniversite);
    }
}
