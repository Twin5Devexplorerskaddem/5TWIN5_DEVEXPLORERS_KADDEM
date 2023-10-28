package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import tn.esprit.spring.kaddem.entities.Contrat;

import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@SpringJUnitConfig
public class ContratServiceImplWithMocksTest {

    @Autowired
    private ContratServiceImpl contratService;

    @MockBean  // Use @MockBean to mock the repository
    private ContratRepository contratRepository;

    @BeforeEach
    public void setup() {
        // Create contracts for testing
        Date dateSysteme = new Date();

        // Contract 1
        Contrat contrat1 = new Contrat();
        contrat1.setDateDebutContrat(new Date(dateSysteme.getTime() - (30 * 24 * 60 * 60 * 1000))); // 30 days ago
        contrat1.setDateFinContrat(dateSysteme);
        contrat1.setSpecialite(Specialite.IA);
        contrat1.setArchive(false);

        // Contract 2
        Contrat contrat2 = new Contrat();
        contrat2.setDateDebutContrat(new Date(dateSysteme.getTime() - (60 * 24 * 60 * 60 * 1000))); // 60 days ago
        contrat2.setDateFinContrat(dateSysteme);
        contrat2.setSpecialite(Specialite.CLOUD);
        contrat2.setArchive(false);

        // Create a list of contracts
        List<Contrat> contractList = Arrays.asList(contrat1, contrat2);

        // Use Mockito to mock the behavior of the repository's findAll method
        when(contratRepository.findAll()).thenReturn(contractList);
    }

    @Test
    public void testGetChiffreAffaireEntreDeuxDates() {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() - (15 * 24 * 60 * 60 * 1000));

        float chiffreAffaire = contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);

        // Perform your assertions here
        assertEquals(-350.0, chiffreAffaire, 0.01);
    }
}