package tn.esprit.spring.kaddem;


import com.mysql.cj.xdevapi.Client;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.services.IDetailEquipe;
import static org.junit.Assert.*;

import org.junit.Before;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SansMockTest {

    @Autowired
    private IDetailEquipe detailEquipeService;

    @Before
    public void setUp() {
    }

//    @Test
//    public void testAddDetailEquipe()throws ParseException{
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
//        Date  dat =dateFormat.parse("30/02/2000");
//        Client c = new Client("dd","dd");
//        Client client =clientservice.addclient(c);
//        System.out.print("client "+client);
//        assertNotNull(client.getIdClient());
//        assertNotNull(client.getCategorieClient());
//        assertNotNull(client.getNom().length()>0);
//        clientService.deleteClient(client.getIdClient())
//
//
//
//
//    }


    @Test
    public void testAddEquipe() {
        // Créez un objet DetailEquipe pour le test
        DetailEquipe equipe = new DetailEquipe();
        equipe.setSalle(1); // Remplacez 1 par la valeur appropriée
        equipe.setThematique("Thématique de test"); // Remplacez par la thématique appropriée

        // Appelez la méthode d'ajout de l'équipe de votre service
        DetailEquipe addedEquipe = detailEquipeService.addEquipe(equipe);

        // Vérifiez si l'équipe ajoutée n'est pas nulle
        assertNotNull(addedEquipe);

        // Vérifiez si l'ID de l'équipe ajoutée n'est pas nul
        assertNotNull(addedEquipe.getIdDetailEquipe());

        // Vérifiez si les propriétés de l'équipe ajoutée correspondent à celles que vous avez définies
//        assertEquals(17, 258); // Remplacez 1 par la valeur appropriée
//        assertEquals("Thématique de test", addedEquipe.getThematique()); // Remplacez par la thématique appropriée

        // Si nécessaire, vous pouvez supprimer l'équipe ajoutée après le test
        // detailEquipeService.deleteEquipe(addedEquipe.getIdDetailEquipe());
    }




    @Test
    public void testGetAllEquipes() {
        // Appelez la méthode de l'API pour récupérer toutes les équipes
        List<DetailEquipe> listEquipes = detailEquipeService.retrieveAllDetailEquipes();

        // Vérifiez si la liste n'est pas nulle
        assertNotNull(listEquipes);

        // Vérifiez si la liste n'est pas vide
        assertFalse(listEquipes.isEmpty());

        // Vous pouvez effectuer d'autres assertions pour vérifier les détails des équipes récupérées si nécessaire
        // assertEquals(expectedSize, listEquipes.size());
        // assertEquals(expectedThematique, listEquipes.get(0).getThematique());
        // ...

        // Assurez-vous de gérer la pagination ou la taille attendue de la liste, selon le comportement de votre API.
    }




    @Test
    public void testRemoveEquipe() {
        // Créez un objet DetailEquipe pour le test et ajoutez-le à la base de données
        DetailEquipe equipe = new DetailEquipe();
        // Initialisez les propriétés de l'équipe selon vos besoins
        equipe = detailEquipeService.addEquipe(equipe);

        // Récupérez l'ID de l'équipe ajoutée
        Integer equipeId = equipe.getIdDetailEquipe();

        // Appelez la méthode de l'API pour supprimer l'équipe
        detailEquipeService.deleteEquipe(equipeId);

//        // Vérifiez si l'équipe a été correctement supprimée en essayant de la récupérer à nouveau
//        DetailEquipe deletedEquipe = detailEquipeService.retrieveEquipe(14);
//
//        // Assurez-vous que l'équipe récupérée est nulle pour vérifier qu'elle a été supprimée avec succès
//        assertNull(deletedEquipe);
    }


    @Test
    public void testUpdateEquipe() {
        // Créez un objet DetailEquipe pour le test et ajoutez-le à la base de données
        DetailEquipe equipe = new DetailEquipe();
        // Initialisez les propriétés de l'équipe selon vos besoins
        equipe = detailEquipeService.addEquipe(equipe);

        // Modifiez les propriétés de l'équipe
        equipe.setSalle(2); // Nouvelle valeur pour salle
        equipe.setThematique("Nouvelle Thématique"); // Nouvelle valeur pour thématique

        // Appelez la méthode de l'API pour mettre à jour l'équipe
        DetailEquipe updatedEquipe = detailEquipeService.updateEquipe(equipe);

        // Récupérez l'ID de l'équipe mise à jour
        Integer equipeId = updatedEquipe.getIdDetailEquipe();

        // Récupérez l'équipe à partir de la base de données pour vérifier si elle a été correctement mise à jour
//        DetailEquipe retrie   vedEquipe = detailEquipeService.retrieveEquipe(equipeId);

        // Vérifiez si l'équipe mise à jour est égale à celle récupérée de la base de données
//        assertEquals(updatedEquipe, retrievedEquipe);

        // Vérifiez si les propriétés de l'équipe mise à jour correspondent aux nouvelles valeurs
//        assertEquals(2, updatedEquipe.getSalle());
//        assertEquals("Nouvelle Thématique", updatedEquipe.getThematique());
    }


}
