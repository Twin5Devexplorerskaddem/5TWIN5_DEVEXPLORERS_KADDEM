package tn.esprit.spring.kaddem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Universite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUniv;
    private String nomUniv;
    private String localisation; // Add localisation attribute
    private String description;  // Add description attribute
    private String email;        // Add email attribute

    public Universite() {
        // TODO Auto-generated constructor stub
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
