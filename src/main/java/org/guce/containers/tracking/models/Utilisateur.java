/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.models;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author penda
 */
@Entity
@Table(name = "UTILISATEURS")
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_UTILISATEUR")
    private Long idUtilisateur;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "MOT_DE_PASSE")
    private String motDePasse;
    @Column(name = "PRENOM")
    private String prenom;
    @Column(name = "NOM")
    private String nom;
    @Column(name = "NUMERO_TELEPHONE")
    private String numeroTelephone;
    

    public Utilisateur() {
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.idUtilisateur);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utilisateur other = (Utilisateur) obj;
        if (!Objects.equals(this.idUtilisateur, other.idUtilisateur)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "idUtilisateur=" + idUtilisateur + ", email=" + email + ", motDePasse=" + motDePasse + ", prenom=" + prenom + ", nom=" + nom + ", numeroTelephone=" + numeroTelephone + '}';
    }

    
}
