/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author penda
 */
@Entity
@Table(name = "MOUVEMENTS_CONTENEURS")
public class MouvementConteneur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_MOUVEMENT")
    private Long idMouvement;
    @Column(name = "DATE_MOUVEMENT")
    @Temporal(TemporalType.DATE)
    private Date dateMouvement;
    @Column(name = "NUMERO_CONTENEUR")
    private String numeroConteneur;
    @Column(name = "HEURE_MOUVEMENT")
    @Temporal(TemporalType.TIME)
    private Date heureMouvement;
    @Column(name = "TYPE_MOUVEMENT")
    private String typeMouvement;
    @Column(name = "TYPE_CONTENEUR")
    private String typeConteneur;
    @Column(name = "TAILLE_CONTENEUR")
    private String tailleConteneur;
    @Column(name = "NUMERO_BL")
    private String numeroBL;
    @Column(name = "NOM_NAVIRE")
    private String nomNavire;
    @Column(name = "NUMERO_VOYAGE")
    private String numeroVoyage;
    @Column(name = "LIEU_DEPART")
    private String lieuDepart;
    @Column(name = "LIEU_ARRIVEE")
    private String lieuArrivee;
    @Column(name = "DERNIERE_LOCALISATION")
    private String derniereLocalisation;
    @Column(name = "LATITUDE")
    private Double latitude;
    @Column(name = "LONGITUDE")
    private Double longitude;
    @Column(name = "DATE_PREVISIONNELLE")
    @Temporal(TemporalType.DATE)
    private Date datePrevisionnelle;
    @ManyToOne
    @JoinColumn(name = "ID_CONTENEUR")
    private Conteneur idConteneur;

    public MouvementConteneur() {
    }

    public Long getIdMouvement() {
        return idMouvement;
    }

    public void setIdMouvement(Long idMouvement) {
        this.idMouvement = idMouvement;
    }

    public Date getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(Date dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public Date getHeureMouvement() {
        return heureMouvement;
    }

    public void setHeureMouvement(Date heureMouvement) {
        this.heureMouvement = heureMouvement;
    }

    public String getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(String typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    public String getNumeroBL() {
        return numeroBL;
    }

    public void setNumeroBL(String numeroBL) {
        this.numeroBL = numeroBL;
    }

    public String getNomNavire() {
        return nomNavire;
    }

    public void setNomNavire(String nomNavire) {
        this.nomNavire = nomNavire;
    }

    public String getNumeroVoyage() {
        return numeroVoyage;
    }

    public void setNumeroVoyage(String numeroVoyage) {
        this.numeroVoyage = numeroVoyage;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public String getLieuArrivee() {
        return lieuArrivee;
    }

    public void setLieuArrivee(String lieuArrivee) {
        this.lieuArrivee = lieuArrivee;
    }

    public String getDerniereLocalisation() {
        return derniereLocalisation;
    }

    public void setDerniereLocalisation(String derniereLocalisation) {
        this.derniereLocalisation = derniereLocalisation;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getDatePrevisionnelle() {
        return datePrevisionnelle;
    }

    public void setDatePrevisionnelle(Date datePrevisionnelle) {
        this.datePrevisionnelle = datePrevisionnelle;
    }

    public Conteneur getIdConteneur() {
        return idConteneur;
    }

    public void setIdConteneur(Conteneur idConteneur) {
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
    
    

    public Map<String, Object> toMap(){
        Map<String, Object> mouvementConteneurMap = new HashMap<>();
        mouvementConteneurMap.put("idMouvement", this.idMouvement);
        mouvementConteneurMap.put("numeroConteneur", this.numeroConteneur);
        mouvementConteneurMap.put("latitude", this.latitude);
        mouvementConteneurMap.put("longitude", this.longitude);
        return mouvementConteneurMap;
    }
}
