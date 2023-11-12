package tn.esprit.spring.kaddem.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Universite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUniv;
    private String nomUniv;
    private String localisation; // Add localisation attribute
    private String description;  // Add description attribute
    private String email;        // Add email attribute

    public Universite() {
    }

    public Universite(String nomUniv, String localisation, String description, String email) {
        super();
        this.nomUniv = nomUniv;
        this.localisation = localisation;
        this.description = description;
        this.email = email;
    }

    public Universite(Integer idUniv, String nomUniv, String localisation, String description, String email) {
        super();
        this.idUniv = idUniv;
        this.nomUniv = nomUniv;
        this.localisation = localisation;
        this.description = description;
        this.email = email;
    }

    public Integer getIdUniv() {
        return idUniv;
    }

    public void setIdUniv(Integer idUniv) {
        this.idUniv = idUniv;
    }

    public String getNomUniv() {
        return nomUniv;
    }

    public void setNomUniv(String nomUniv) {
        this.nomUniv = nomUniv;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
