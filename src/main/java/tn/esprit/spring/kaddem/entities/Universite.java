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

}
