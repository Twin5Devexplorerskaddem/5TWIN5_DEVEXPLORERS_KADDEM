package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.*;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
 class DepartementServiceTestWithoutMock {

    @Autowired
    private DepartementServiceImpl departementService;

    @Autowired
    private DepartementRepository departementRepository;


   @BeforeEach
   public void setUp() {
      Departement departement1 = new Departement("Computer Science");
      departementRepository.save(departement1);

      Departement departement2 = new Departement("Electrical Engineering");
      departementRepository.save(departement2);
   }


   @Test
     void testUpdateDepartement() {
        Departement departement = departementRepository.findByNomDepart("Computer Science");
        departement.setNomDepart("Computer Engineering");
        Departement updatedDepartement = departementService.updateDepartement(departement);
        assertNotNull(updatedDepartement);
        assertEquals("Computer Engineering", updatedDepartement.getNomDepart());

        Departement retrievedDepartement = departementRepository.findById(updatedDepartement.getIdDepart()).orElse(null);
        assertNotNull(retrievedDepartement);
        assertEquals("Computer Engineering", retrievedDepartement.getNomDepart());
    }
    @Test
     void testRetrieveAllDepartements() {
        List<Departement> departements = departementService.retrieveAllDepartements();
        assertFalse(departements.isEmpty());
        assertEquals(2, departements.size());
    }

    @Test
     void testAddDepartement() {
        Departement departement = new Departement("Mechanical Engineering");
        Departement addedDepartement = departementService.addDepartement(departement);
        assertNotNull(addedDepartement);
        assertEquals("Mechanical Engineering", addedDepartement.getNomDepart());

        Departement retrievedDepartement = departementRepository.findById(addedDepartement.getIdDepart()).orElse(null);
        assertNotNull(retrievedDepartement);
        assertEquals("Mechanical Engineering", retrievedDepartement.getNomDepart());
    }


    @Test
     void testRetrieveDepartement() {
        Departement departement = departementService.retrieveDepartement(1L);
        assertNotNull(departement);
        assertEquals("Computer Science", departement.getNomDepart());
    }

    @Test
     void testRemoveDepartement() {
        departementService.deleteDepartement(2L);
        Departement departement = departementService.retrieveDepartement(2L);
        assertNull(departement);
    }

    // Add more test methods as needed for your specific use cases.
}
