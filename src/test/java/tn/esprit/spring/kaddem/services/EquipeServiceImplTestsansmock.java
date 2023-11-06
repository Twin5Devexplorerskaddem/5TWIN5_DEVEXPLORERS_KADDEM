package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Equipe;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EquipeServiceImplTestWithoutMock {

    @Autowired
    private IEquipeService equipeService;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testRetrieveAllEquipes() {
        List<Equipe> equipes = equipeService.retrieveAllEquipes();
        assertNotNull(equipes);
        assertFalse(equipes.isEmpty());

    }

    @Test
    public void testAddEquipe() {
        Equipe equipe = new Equipe("Sample Team");
        Equipe addedEquipe = equipeService.addEquipe(equipe);
        assertNotNull(addedEquipe);
        assertNotNull(addedEquipe.getIdEquipe());
        assertEquals("Sample Team", addedEquipe.getNomEquipe());


    }

    @Test
    public void testUpdateEquipe() {

        Equipe equipe = new Equipe("Sample Team");
        Equipe addedEquipe = equipeService.addEquipe(equipe);

        addedEquipe.setNomEquipe("Updated Team Name");
        Equipe updatedEquipe = equipeService.updateEquipe(addedEquipe);

        assertNotNull(updatedEquipe);
        assertEquals("Updated Team Name", updatedEquipe.getNomEquipe());

    }

    @Test
    @Disabled("Ce test est désactivé car l'équipe avec l'ID 12 n'existe pas.")
    public void testRetrieveEquipe() {

        Equipe equipe = new Equipe("Sample Team");
        Equipe addedEquipe = equipeService.addEquipe(equipe);

        Equipe retrievedEquipe = equipeService.retrieveEquipe(addedEquipe.getIdEquipe());
        assertNotNull(retrievedEquipe);
        assertEquals("Sample Team", retrievedEquipe.getNomEquipe());
    }

    @Test
    @Disabled("Ce test est désactivé car l'équipe avec l'ID 12 n'existe pas.")

    public void testDeleteEquipe() {

        Equipe equipe = new Equipe("Sample Team");
        Equipe addedEquipe = equipeService.addEquipe(equipe);

        int equipeId = addedEquipe.getIdEquipe();
        equipeService.deleteEquipe(equipeId);

        Equipe deletedEquipe = equipeService.retrieveEquipe(equipeId);
        assertNull(deletedEquipe);
    }

    @Test
    public void testEvoluerEquipes() {

    }
}
