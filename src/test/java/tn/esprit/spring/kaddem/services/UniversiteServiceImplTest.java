package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
 class UniversiteServiceImplTest {

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

   /*@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }**/

    @Test
     void testAddUniversite() {
        Universite sampleUniversite = new Universite();
        sampleUniversite.setIdUniv(1);
        sampleUniversite.setNomUniv("Sample University");
        sampleUniversite.setLocalisation("Sample Location");
        sampleUniversite.setDescription("Sample Description");
        sampleUniversite.setEmail("sample@example.com");

        Mockito.when(universiteRepository.save(Mockito.any(Universite.class))).thenReturn(sampleUniversite);

        Universite addedUniversite = universiteService.addUniversite(sampleUniversite);

        Mockito.verify(universiteRepository).save(sampleUniversite);

        assertEquals(1, addedUniversite.getIdUniv());
        assertEquals("Sample University", addedUniversite.getNomUniv());
        assertEquals("Sample Location", addedUniversite.getLocalisation());
        assertEquals("Sample Description", addedUniversite.getDescription());
        assertEquals("sample@example.com", addedUniversite.getEmail());
    }

    @Test
     void testRetrieveAllUniversites() {
       List<Universite> universiteList = getUniversites();

       Mockito.when(universiteRepository.findAll()).thenReturn(universiteList);

        List<Universite> retrievedUniversites = universiteService.retrieveAllUniversites();

        Mockito.verify(universiteRepository).findAll();

        assertEquals(2, retrievedUniversites.size());
        assertEquals(1, retrievedUniversites.get(0).getIdUniv());
        assertEquals("University 1", retrievedUniversites.get(0).getNomUniv());
        assertEquals("Location 1", retrievedUniversites.get(0).getLocalisation());
        assertEquals("Description 1", retrievedUniversites.get(0).getDescription());
        assertEquals("email1@example.com", retrievedUniversites.get(0).getEmail());
        assertEquals(2, retrievedUniversites.get(1).getIdUniv());
        assertEquals("University 2", retrievedUniversites.get(1).getNomUniv());
        assertEquals("Location 2", retrievedUniversites.get(1).getLocalisation());
        assertEquals("Description 2", retrievedUniversites.get(1).getDescription());
        assertEquals("email2@example.com", retrievedUniversites.get(1).getEmail());
    }

   private static List<Universite> getUniversites() {
      Universite universite1 = new Universite();
      universite1.setIdUniv(1);
      universite1.setNomUniv("University 1");
      universite1.setLocalisation("Location 1");
      universite1.setDescription("Description 1");
      universite1.setEmail("email1@example.com");

      Universite universite2 = new Universite();
      universite2.setIdUniv(2);
      universite2.setNomUniv("University 2");
      universite2.setLocalisation("Location 2");
      universite2.setDescription("Description 2");
      universite2.setEmail("email2@example.com");

      List<Universite> universiteList = new ArrayList<>();
      universiteList.add(universite1);
      universiteList.add(universite2);
      return universiteList;
   }

   @Test
     void testUpdateUniversite() {
        Universite sampleUniversite = new Universite();
        sampleUniversite.setIdUniv(1);
        sampleUniversite.setNomUniv("Sample University");
        sampleUniversite.setLocalisation("Sample Location");
        sampleUniversite.setDescription("Sample Description");
        sampleUniversite.setEmail("sample@example.com");

        Mockito.when(universiteRepository.save(Mockito.any(Universite.class))).thenReturn(sampleUniversite);

        Universite updatedUniversite = universiteService.updateUniversite(sampleUniversite);

        Mockito.verify(universiteRepository).save(sampleUniversite);

        assertEquals(1, updatedUniversite.getIdUniv());
        assertEquals("Sample University", updatedUniversite.getNomUniv());
        assertEquals("Sample Location", updatedUniversite.getLocalisation());
        assertEquals("Sample Description", updatedUniversite.getDescription());
        assertEquals("sample@example.com", updatedUniversite.getEmail());
    }

    @Test
     void testRetrieveUniversite() {
        Universite sampleUniversite = new Universite();
        sampleUniversite.setIdUniv(1);
        sampleUniversite.setLocalisation("Sample Location");
        sampleUniversite.setDescription("Sample Description");
        sampleUniversite.setEmail("sample@example.com");

        Mockito.when(universiteRepository.findById(1)).thenReturn(Optional.of(sampleUniversite));

        Universite retrievedUniversite = universiteService.retrieveUniversite(1);

        Mockito.verify(universiteRepository).findById(1);

        assertEquals(1, retrievedUniversite.getIdUniv());
        assertEquals("Sample Location", retrievedUniversite.getLocalisation());
        assertEquals("Sample Description", retrievedUniversite.getDescription());
        assertEquals("sample@example.com", retrievedUniversite.getEmail());
    }

   @Test
   void testDeleteUniversite() {

      Universite sampleUniversite = new Universite();
      sampleUniversite.setIdUniv(1);
      sampleUniversite.setNomUniv("Sample University");
      sampleUniversite.setLocalisation("Sample Location");
      sampleUniversite.setDescription("Sample Description");
      sampleUniversite.setEmail("sample@example.com");
      Mockito.when(universiteRepository.findById(1)).thenReturn(Optional.of(sampleUniversite));
      universiteService.deleteUniversite(1);
      Mockito.verify(universiteRepository).delete(sampleUniversite);
   }

}
