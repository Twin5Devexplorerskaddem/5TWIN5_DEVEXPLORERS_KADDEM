package tn.esprit.spring.kaddem.services;

import tn.esprit.spring.kaddem.entities.DetailEquipe;
import java.util.List;

public interface IDetailEquipe {
    public List<DetailEquipe> retrieveAllDetailEquipes();
    public DetailEquipe addEquipe(DetailEquipe e);
    public  void deleteEquipe(Integer idEquipe);
    public DetailEquipe updateEquipe(DetailEquipe updatedEquipe);
    public DetailEquipe retrieveEquipe(Integer equipeId);
    public void evoluerEquipes();
}
