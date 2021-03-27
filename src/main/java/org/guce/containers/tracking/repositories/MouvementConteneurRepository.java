/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.repositories;

import java.util.List;
import org.guce.containers.tracking.models.MouvementConteneur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author penda
 */
@Repository
public interface MouvementConteneurRepository extends JpaRepository<MouvementConteneur, Long>{
    List<MouvementConteneur> findByNumeroConteneur(String numeroConteneur, Sort sort);
    List<MouvementConteneur> findByNumeroConteneurContaining(String numeroConteneur, Sort sort);
    Page<MouvementConteneur> findByNumeroConteneurContaining(String numeroConteneur, Pageable pageable);
    List<MouvementConteneur> findByNumeroBL(String numeroBL, Sort sort);
    List<MouvementConteneur> findByNumeroBLContaining(String numeroBL, Sort sort);
    Page<MouvementConteneur> findByNumeroBLContaining(String numeroBL, Pageable pageable);
    List<MouvementConteneur> findByNumeroVoyage(String numeroVoyage, Sort sort);
    List<MouvementConteneur> findByNumeroVoyageContaining(String numeroVoyage, Sort sort);
    Page<MouvementConteneur> findByNumeroVoyageContaining(String numeroVoyage, Pageable pageable);
    Page<MouvementConteneur> findByNumeroConteneurContainingOrNumeroBLContainingOrNumeroVoyageContaining(String numeroConteneur, String numeroBL, String numeroVoyage, Pageable pageable);
    List<MouvementConteneur> findByNumeroConteneurContainingOrNumeroBLContainingOrNumeroVoyageContaining(String numeroConteneur, String numeroBL, String numeroVoyage, Sort sort);
}
