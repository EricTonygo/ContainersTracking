/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.repositories;

import java.util.List;
import org.guce.containers.tracking.models.Conteneur;
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
public interface ConteneurRepository extends JpaRepository<Conteneur, Long>{
    Conteneur findByNumeroConteneur(String numeroConteneur);
    List<Conteneur> findByTypeConteneurContaining(String typeConteneur, Sort sort);
    Page<Conteneur> findByTypeConteneurContaining(String typeConteneur, Pageable pageable);
    List<Conteneur> findByTailleConteneurContaining(String tailleConteneur, Sort sort);
    Page<Conteneur> findByTailleConteneurContaining(String tailleConteneur, Pageable pageable);
    List<Conteneur> findByNumeroConteneurContainingOrTailleConteneurContainingOrTypeConteneurContaining(String numeroConteneur, String tailleConteneur, String typeConteneur, Sort sort);
    Page<Conteneur> findByNumeroConteneurContainingOrTailleConteneurContainingOrTypeConteneurContaining(String numeroConteneur, String tailleConteneur, String typeConteneur, Pageable pageable);
}
