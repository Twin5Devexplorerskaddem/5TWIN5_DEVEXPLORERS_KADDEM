package tn.esprit.spring.kaddem.services;

import tn.esprit.spring.kaddem.entities.Universite;

import java.util.List;

public interface IUniversiteService {
    List<Universite> retrieveAllUniversites();

    Universite addUniversite (Universite  u);

    Universite updateUniversite (Universite  u);

    Universite retrieveUniversite (Integer idUniversite);

      void deleteUniversite(Integer idUniversite);

}
