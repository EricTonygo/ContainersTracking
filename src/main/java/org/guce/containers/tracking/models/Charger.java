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
import javax.persistence.Table;

/**
 *
 * @author penda
 */
@Entity
@Table(name = "CHARGERS")
public class Charger implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TAX_PAYER_NUMBER")
    private String taxpayerNumber;
    @Column(name = "COMPANY_NAME")
    private String companyName;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "MOBILE_PHONE_NUMBER")
    private String mobilePhoneNumber;
    @Column(name = "FIX_PHONE_NUMBER")
    private String fixPhoneNumber;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "FAX")
    private String fax;

    public Charger() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaxpayerNumber() {
        return taxpayerNumber;
    }

    public void setTaxpayerNumber(String taxpayerNumber) {
        this.taxpayerNumber = taxpayerNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getFixPhoneNumber() {
        return fixPhoneNumber;
    }

    public void setFixPhoneNumber(String fixPhoneNumber) {
        this.fixPhoneNumber = fixPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Charger other = (Charger) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Charger{" + "id=" + id + ", taxpayerNumber=" + taxpayerNumber + ", companyName=" + companyName + ", address=" + address + ", mobilePhoneNumber=" + mobilePhoneNumber + ", fixPhoneNumber=" + fixPhoneNumber + ", email=" + email + ", fax=" + fax + '}';
    }
}
