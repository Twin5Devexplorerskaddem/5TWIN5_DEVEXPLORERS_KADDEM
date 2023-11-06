package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EquipeServiceImplTest {

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @Mock
    private EquipeRepository equipeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        equipeService = new EquipeServiceImpl(equipeRepository);
    }


    @Test
    public void testAddEquipe() {
        Equipe sampleEquipe = new Equipe();
        sampleEquipe.setIdEquipe(1);
        sampleEquipe.setNomEquipe("Sample Team");
        sampleEquipe.setNiveau(Niveau.JUNIOR);

        when(equipeRepository.save(Mockito.any(Equipe.class))).thenReturn(sampleEquipe);

        Equipe addedEquipe = equipeService.addEquipe(sampleEquipe);

        verify(equipeRepository).save(sampleEquipe);

        assertEquals(1, addedEquipe.getIdEquipe());
        assertEquals("Sample Team", addedEquipe.getNomEquipe());
        assertEquals(Niveau.JUNIOR, addedEquipe.getNiveau());
    }

    @Test
    public void testRetrieveAllEquipes() {
        Equipe equipe1 = new Equipe();
        equipe1.setIdEquipe(1);
        equipe1.setNomEquipe("Team 1");
        equipe1.setNiveau(Niveau.JUNIOR);

        Equipe equipe2 = new Equipe();
        equipe2.setIdEquipe(2);
        equipe2.setNomEquipe("Team 2");
        equipe2.setNiveau(Niveau.SENIOR);

        List<Equipe> equipeList = new ArrayList<>();
        equipeList.add(equipe1);
        equipeList.add(equipe2);

        when(equipeRepository.findAll()).thenReturn(equipeList);

        List<Equipe> retrievedEquipes = equipeService.retrieveAllEquipes();

        verify(equipeRepository).findAll();

        assertEquals(2, retrievedEquipes.size());
        assertEquals(1, retrievedEquipes.get(0).getIdEquipe());
        assertEquals("Team 1", retrievedEquipes.get(0).getNomEquipe());
        assertEquals(Niveau.JUNIOR, retrievedEquipes.get(0).getNiveau());
        assertEquals(2, retrievedEquipes.get(1).getIdEquipe());
        assertEquals("Team 2", retrievedEquipes.get(1).getNomEquipe());
        assertEquals(Niveau.SENIOR, retrievedEquipes.get(1).getNiveau());
    }

    @Test
    public void testUpdateEquipe() {
        Equipe sampleEquipe = new Equipe();
        sampleEquipe.setIdEquipe(1);
        sampleEquipe.setNomEquipe("Sample Team");
        sampleEquipe.setNiveau(Niveau.JUNIOR);

        when(equipeRepository.save(Mockito.any(Equipe.class))).thenReturn(sampleEquipe);

        Equipe updatedEquipe = equipeService.updateEquipe(sampleEquipe);

        verify(equipeRepository).save(sampleEquipe);

        assertEquals(1, updatedEquipe.getIdEquipe());
        assertEquals("Sample Team", updatedEquipe.getNomEquipe());
        assertEquals(Niveau.JUNIOR, updatedEquipe.getNiveau());
    }

    @Test
    public void testRetrieveEquipe() {
        Equipe sampleEquipe = new Equipe();
        sampleEquipe.setIdEquipe(1);
        sampleEquipe.setNomEquipe("Sample Team");
        sampleEquipe.setNiveau(Niveau.JUNIOR);

        when(equipeRepository.findById(1)).thenReturn(Optional.of(sampleEquipe));

        Equipe retrievedEquipe = equipeService.retrieveEquipe(1);

        verify(equipeRepository).findById(1);

        assertEquals(1, retrievedEquipe.getIdEquipe());
        assertEquals("Sample Team", retrievedEquipe.getNomEquipe());
        assertEquals(Niveau.JUNIOR, retrievedEquipe.getNiveau());
    }



    @Test

    public void testDeleteEquipe() {
        Equipe sampleEquipe = new Equipe();
        sampleEquipe.setIdEquipe(1);
        sampleEquipe.setNomEquipe("Sample Team");
        sampleEquipe.setNiveau(Niveau.JUNIOR);

        when(equipeRepository.findById(1)).thenReturn(Optional.of(sampleEquipe));

        equipeService.deleteEquipe(1);

        verify(equipeRepository).delete(sampleEquipe);
    }


}
