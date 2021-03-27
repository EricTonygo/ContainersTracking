/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.repositories;

import org.guce.containers.tracking.models.HistoriqueRechercheConteneur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author penda
 */
@Repository
public interface HistoriqueRechercheConteneurRepository extends JpaRepository<HistoriqueRechercheConteneur, Long>{
    
}
