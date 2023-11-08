package tn.esprit.spring.kaddem.services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.List;
@AllArgsConstructor
@Service
public class UniversiteServiceImpl implements IUniversiteService{

    UniversiteRepository universiteRepository;


  public   List<Universite> retrieveAllUniversites(){
return (List<Universite>) universiteRepository.findAll();
    }

 public    Universite addUniversite (Universite  u){
return  (universiteRepository.save(u));
    }

 public    Universite updateUniversite (Universite  u){
     return  (universiteRepository.save(u));
    }

  public Universite retrieveUniversite (Integer idUniversite){
return universiteRepository.findById(idUniversite).orElse(null);
    }
    public  void deleteUniversite(Integer idUniversite){
        universiteRepository.delete(retrieveUniversite(idUniversite));
    }

}
