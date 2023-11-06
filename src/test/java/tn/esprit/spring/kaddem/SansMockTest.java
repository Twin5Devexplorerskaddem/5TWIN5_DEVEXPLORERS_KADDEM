package tn.esprit.spring.kaddem;


import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import com.fasterxml.jackson.databind.ObjectMapper;
import tn.esprit.spring.kaddem.services.DetailEquipeServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SansMockTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetEquipes() throws Exception {
        // Effectuez une requête GET sur votre endpoint
        mockMvc.perform(get("/Detailequipe/retrieve-all-detailequipes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5))) // Vérifiez que vous avez 2 éléments dans la liste (ajustez le cas échéant)
                .andExpect(jsonPath("[0].idDetailEquipe", is(6))) // Vérifiez les détails des éléments
                .andExpect(jsonPath("[0].salle", is(258)))
                .andExpect(jsonPath("[0].thematique", is("Sample Thematique")))
                .andExpect(jsonPath("[1].idDetailEquipe", is(5)))
                .andExpect(jsonPath("[1].salle", is(123)));
    }

    @Test
    public void testAddEquipe() throws Exception {
        DetailEquipe equipeToAdd = new DetailEquipe();
        equipeToAdd.setSalle(258);
        equipeToAdd.setThematique("Sample Thematique");

        // Convertir l'objet en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(equipeToAdd);

        mockMvc.perform(post("/Detailequipe/add-detailequipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salle").value(258))
                .andExpect(jsonPath("$.thematique").value("Sample Thematique"));
    }

    @Test
    public void testRemoveEquipe() throws Exception {
        Integer equipeId = 12; // Remplacez par l'ID de l'équipe que vous souhaitez supprimer

        mockMvc.perform(delete("/Detailequipe/remove-detailequipe/{idDetailEquipe}", equipeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Vous pouvez ajouter des assertions supplémentaires ici pour vérifier que l'équipe a été supprimée avec succès, par exemple, en essayant de la récupérer et en vous assurant qu'elle n'existe pas.

        // Par exemple, vous pouvez ajouter un test pour vérifier que l'équipe a été supprimée en essayant de la récupérer et en vous assurant qu'elle n'existe pas.
//        mockMvc.perform(get("/Detailequipe/retrieve-detailequipe/{idDetailEquipe}", equipeId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound()); // Vérifiez que l'équipe n'existe pas.

        // Assurez-vous que l'équipe a été supprimée avec succès en vérifiant son absence dans la base de données ou en effectuant d'autres validations appropriées.
    }



//    @Test
//    public void testRemoveEquipe() throws Exception {
//        Integer equipeId = 1; // Remplacez par l'ID de l'équipe que vous souhaitez supprimer
//
//        mockMvc.perform(delete("/Detailequipe/remove-detailequipe/{idDetailEquipe}", equipeId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//        // Vérifiez ici que l'équipe a été supprimée avec succès, par exemple, en essayant de la récupérer et en vous assurant qu'elle n'existe pas.
//    }


    @Test
    public void testUpdateEquipe() throws Exception {
        // Créez une équipe avec les modifications que vous souhaitez apporter
        DetailEquipe updatedEquipe = new DetailEquipe();
        updatedEquipe.setIdDetailEquipe(5); // ID de l'équipe à mettre à jour
        updatedEquipe.setSalle(14789); // Nouvelle salle
        updatedEquipe.setThematique("difficile Thematique"); // Nouvelle thématique

        // Convertissez l'objet en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(updatedEquipe);

        mockMvc.perform(put("/Detailequipe/update-detailequipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());


    }
}
