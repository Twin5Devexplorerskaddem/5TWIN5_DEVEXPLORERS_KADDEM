package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UniversiteServiceImplTest {

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddUniversite() {
        Universite sampleUniversite = new Universite();
        sampleUniversite.setIdUniv(1);
        sampleUniversite.setNomUniv("Sample University");

        Mockito.when(universiteRepository.save(Mockito.any(Universite.class))).thenReturn(sampleUniversite);

        Universite addedUniversite = universiteService.addUniversite(sampleUniversite);

        Mockito.verify(universiteRepository).save(sampleUniversite);

        assertEquals(1, addedUniversite.getIdUniv());
        assertEquals("Sample University", addedUniversite.getNomUniv());
    }



    @Test
    public void testRetrieveAllUniversites() {
        Universite universite1 = new Universite();
        universite1.setIdUniv(1);
        universite1.setNomUniv("University 1");

        Universite universite2 = new Universite();
        universite2.setIdUniv(2);
        universite2.setNomUniv("University 2");

        List<Universite> universiteList = new ArrayList<>();
        universiteList.add(universite1);
        universiteList.add(universite2);

        Mockito.when(universiteRepository.findAll()).thenReturn(universiteList);

        List<Universite> retrievedUniversites = universiteService.retrieveAllUniversites();

        Mockito.verify(universiteRepository).findAll();

        assertEquals(2, retrievedUniversites.size());
        assertEquals(1, retrievedUniversites.get(0).getIdUniv());
        assertEquals("University 1", retrievedUniversites.get(0).getNomUniv());
        assertEquals(2, retrievedUniversites.get(1).getIdUniv());
        assertEquals("University 2", retrievedUniversites.get(1).getNomUniv());
    }

    @Test
    public void testUpdateUniversite() {
        Universite sampleUniversite = new Universite();
        sampleUniversite.setIdUniv(1);
        sampleUniversite.setNomUniv("Sample University");

        Mockito.when(universiteRepository.save(Mockito.any(Universite.class))).thenReturn(sampleUniversite);

        Universite updatedUniversite = universiteService.updateUniversite(sampleUniversite);

        Mockito.verify(universiteRepository).save(sampleUniversite);

        assertEquals(1, updatedUniversite.getIdUniv());
        assertEquals("Sample University", updatedUniversite.getNomUniv());
    }

    @Test
    public void testRetrieveUniversite() {
        Universite sampleUniversite = new Universite();
        sampleUniversite.setIdUniv(1);

        Mockito.when(universiteRepository.findById(1)).thenReturn(Optional.of(sampleUniversite));

        Universite retrievedUniversite = universiteService.retrieveUniversite(1);

        Mockito.verify(universiteRepository).findById(1);

        assertEquals(1, retrievedUniversite.getIdUniv());
    }
}
