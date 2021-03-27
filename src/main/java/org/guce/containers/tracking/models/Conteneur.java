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
@Table(name = "CONTENEURS")
public class Conteneur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CONTENEUR")
    private Long idConteneur;
    @Column(name = "NUMERO_CONTENEUR")
    private String numeroConteneur;
    @Column(name = "TYPE_CONTENEUR")
    private String typeConteneur;
    @Column(name = "TAILLE_CONTENEUR")
    private String tailleConteneur;

    public Conteneur() {
    }

    public Long getIdConteneur() {
        return idConteneur;
    }

    public void setIdConteneur(Long idConteneur) {
        this.idConteneur = idConteneur;
    }

    public String getNumeroConteneur() {
        return numeroConteneur;
    }

    public void setNumeroConteneur(String numeroConteneur) {
        this.numeroConteneur = numeroConteneur;
    }

    public String getTypeConteneur() {
        return typeConteneur;
    }

    public void setTypeConteneur(String typeConteneur) {
        this.typeConteneur = typeConteneur;
    }

    public String getTailleConteneur() {
        return tailleConteneur;
    }

    public void setTailleConteneur(String tailleConteneur) {
        this.tailleConteneur = tailleConteneur;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.idConteneur);
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
        final Conteneur other = (Conteneur) obj;
        if (!Objects.equals(this.idConteneur, other.idConteneur)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Conteneur{" + "idConteneur=" + idConteneur + ", numeroConteneur=" + numeroConteneur + ", typeConteneur=" + typeConteneur + ", tailleConteneur=" + tailleConteneur + '}';
    }

    

}
