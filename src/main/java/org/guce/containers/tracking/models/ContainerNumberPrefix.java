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
@Table(name = "CONTAINER_NUMBER_PREFIX")
public class ContainerNumberPrefix implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "PREFIX_STRING")
    private String prefixString;
    @ManyToOne
    @JoinColumn(name = "CONSIGNEE_ID", nullable = true)
    private Consignee consigneeId;

    public ContainerNumberPrefix() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrefixString() {
        return prefixString;
    }

    public void setPrefixString(String prefixString) {
        this.prefixString = prefixString;
    }

    public Consignee getConsigneeId() {
        return consigneeId;
    }

    public void setConsigneeId(Consignee consigneeId) {
        this.consigneeId = consigneeId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final ContainerNumberPrefix other = (ContainerNumberPrefix) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContainerNumberPrefix{" + "id=" + id + ", prefixString=" + prefixString + ", consigneeId=" + consigneeId + '}';
    }
    
    
}
