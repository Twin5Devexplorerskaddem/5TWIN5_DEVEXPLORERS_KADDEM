package tn.esprit.spring.kaddem;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import com.fasterxml.jackson.databind.ObjectMapper;
import tn.esprit.spring.kaddem.services.DetailEquipeServiceImpl;

import static org.mockito.Mockito.when;
@SpringBootTest
@AutoConfigureMockMvc
public class AvecMockTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DetailEquipeServiceImpl equipeService;

    @Test
    public void testGetEquipes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Detailequipe/retrieve-all-detailequipes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }



//    @Test
//    public void testAddEquipe() throws Exception {
//
//        DetailEquipe detailEquipe = new DetailEquipe();
//        detailEquipe.setSalle(258);
//        detailEquipe.setThematique("Sample Thematique");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String requestBody = objectMapper.writeValueAsString(detailEquipe);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/Detailequipe/add-detailequipe")
//                        .content(requestBody)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.salle").value(258))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.thematique").value("Sample Thematique"));
//    }
@Test
public void testAddEquipe() throws Exception {
    // Créez un objet DetailEquipe à envoyer dans la requête POST
    DetailEquipe detailEquipe = new DetailEquipe();
    detailEquipe.setSalle(258);
    detailEquipe.setThematique("Sample Thematique");

    // Configurez ObjectMapper pour sérialiser l'objet en JSON
    ObjectMapper objectMapper = new ObjectMapper();
    String requestBody = objectMapper.writeValueAsString(detailEquipe);

    // Configurez le comportement simulé du service pour ajouter une équipe
    when(equipeService.addEquipe(Mockito.any(DetailEquipe.class))).thenReturn(detailEquipe);

    // Effectuez la requête POST pour ajouter une équipe
    mockMvc.perform(post("/Detailequipe/add-detailequipe")
                    .content(requestBody)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.salle").value(258))
            .andExpect(jsonPath("$.thematique").value("Sample Thematique"));
}


    @Test
    public void testRetrieveEquipe() throws Exception {
        // Replace the expected values with the actual values from your API
        Integer equipeId = 2;
        String expectedSalle = "123"; // Replace with the actual salle value
        String expectedThematique = "Sample Thematique"; // Replace with the actual thematique value

        mockMvc.perform(MockMvcRequestBuilders.get("/Detailequipe/retrieve-detailequipe/{equipe-id}", equipeId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idDetailEquipe").value(equipeId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.salle").value(expectedSalle))
                .andExpect(MockMvcResultMatchers.jsonPath("$.thematique").value(expectedThematique));
    }

    @Test
    public void testRemoveEquipe() throws Exception {
        Integer equipeIdToRemove = 3;

        mockMvc.perform(MockMvcRequestBuilders.delete("/Detailequipe/remove-detailequipe/{idDetailEquipe}", equipeIdToRemove))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

//
//    @Test
//    public void testUpdateEquipe() throws Exception {
//        // Create an updated DetailEquipe
//        DetailEquipe updatedEquipe = new DetailEquipe();
//        updatedEquipe.setIdDetailEquipe(4); // Replace with the ID of the entity you want to update
//        updatedEquipe.setSalle(456); // New salle value
//
//        // Mock the updateEquipe method to return the updated entity
//        when(equipeService.updateEquipe(Mockito.any(DetailEquipe.class))).thenReturn(updatedEquipe);
//
//        // Perform the PUT request to update the entity
//        mockMvc.perform(put("/update-detailequipe")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"idDetailEquipe\": 4, \"salle\": 456}"))
//                .andExpect(status().isOk());
//
//
//
//    }
}