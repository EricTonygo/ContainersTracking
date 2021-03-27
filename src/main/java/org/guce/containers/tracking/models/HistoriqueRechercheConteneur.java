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
@Table(name = "HISTORIQUE_RECHERCHE_CONTENEUR")
public class HistoriqueRechercheConteneur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_HISTORIQUE")
    private Long idHistorique;
    @ManyToOne
    @JoinColumn(name = "ID_CONTENEUR")
    private Conteneur idConteneur;
    @ManyToOne
    @JoinColumn(name = "ID_UTILISATEUR")
    private Utilisateur idUtilisateur;

    public HistoriqueRechercheConteneur() {
    }

    public Long getIdHistorique() {
        return idHistorique;
    }

    public void setIdHistorique(Long idHistorique) {
        this.idHistorique = idHistorique;
    }

    public Conteneur getIdConteneur() {
        return idConteneur;
    }

    public void setIdConteneur(Conteneur idConteneur) {
        this.idConteneur = idConteneur;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.idHistorique);
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
        final HistoriqueRechercheConteneur other = (HistoriqueRechercheConteneur) obj;
        if (!Objects.equals(this.idHistorique, other.idHistorique)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HistoriqueRechercheConteneur{" + "idHistorique=" + idHistorique + ", idConteneur=" + idConteneur + ", idUtilisateur=" + idUtilisateur + '}';
    }

    

}
