package tn.esprit.spring.kaddem.entities;
import lombok.Getter;

import java.io.Serializable;
import javax.persistence.*;
@Getter
@Entity
public class Universite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUniv;
    private String nomUniv;
    private String localisation;
    private String description;
    private String email;

    public Universite() {
    }

    public Universite(String nomUniv, String localisation, String description, String email) {
        super();
        this.nomUniv = nomUniv;
        this.localisation = localisation;
        this.description = description;
        this.email = email;
    }


    public void setIdUniv(Integer idUniv) {
        this.idUniv = idUniv;
    }

    public void setNomUniv(String nomUniv) {
        this.nomUniv = nomUniv;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
