/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author penda
 */
@Entity
@Table(name = "CONTAINERS_MOVEMENT")
public class ContainerMovement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USERNAME")
    private String containerNumber;
    @Column(name = "MOVEMENT_DATE")
    @Temporal(TemporalType.DATE)
    private Date movementDate;
    @Column(name = "MOVEMENT_HOUR")
    @Temporal(TemporalType.TIME)
    private Date movementHour;
    @Column(name = "MOVEMENT_TYPE")
    private String movementType;
    @Column(name = "BL_NUMBER")
    private String blNumber;
    @Column(name = "VESSEL_NAME")
    private String vesselName;
    @Column(name = "CONTAINER_TYPE")
    private String containerType;
    @Column(name = "CONTAINER_SIZE")
    private String containerSize;
    @Column(name = "TRIP_NUMBER")
    private String tripNumber;
    @Column(name = "DEPARTURE_PLACE")
    private String departurePlace;
    @Column(name = "ARRIVAL_PLACE")
    private String arrivalPlace;
    @Column(name = "LATITUDE")
    private String latitude;
    @Column(name = "LONGITUDE")
    private String longitude;

    public ContainerMovement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContainerNumber() {
        return containerNumber;
    }

    public void setContainerNumber(String containerNumber) {
        this.containerNumber = containerNumber;
    }

    public Date getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(Date movementDate) {
        this.movementDate = movementDate;
    }

    public Date getMovementHour() {
        return movementHour;
    }

    public void setMovementHour(Date movementHour) {
        this.movementHour = movementHour;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public String getBlNumber() {
        return blNumber;
    }

    public void setBlNumber(String blNumber) {
        this.blNumber = blNumber;
    }

    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public String getContainerSize() {
        return containerSize;
    }

    public void setContainerSize(String containerSize) {
        this.containerSize = containerSize;
    }

    public String getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(String tripNumber) {
        this.tripNumber = tripNumber;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }

    public String getArrivalPlace() {
        return arrivalPlace;
    }

    public void setArrivalPlace(String arrivalPlace) {
        this.arrivalPlace = arrivalPlace;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final ContainerMovement other = (ContainerMovement) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContainerMovement{" + "id=" + id + ", containerNumber=" + containerNumber + ", movementDate=" + movementDate + ", movementHour=" + movementHour + ", movementType=" + movementType + ", blNumber=" + blNumber + ", vesselName=" + vesselName + ", containerType=" + containerType + ", containerSize=" + containerSize + ", tripNumber=" + tripNumber + ", departurePlace=" + departurePlace + ", arrivalPlace=" + arrivalPlace + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

}
