package tn.esprit.spring.kaddem.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipeDTO {
    private Integer idEquipe;
    private String nomEquipe;
    private Niveau niveau;
    private Set<Etudiant> etudiants;
    private DetailEquipe detailEquipe;
}
