/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services;

import java.util.List;
import org.guce.containers.tracking.models.Conteneur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author penda
 */
public interface IConteneurService {
    public List<Conteneur> findAll();
    public Conteneur save(Conteneur c);
    public Conteneur update(Conteneur c);
    public void delete(Conteneur c);
    public Conteneur getContainer(Long id);
    Conteneur findByNumeroConteneur(String numeroConteneur);
    List<Conteneur> findByTypeConteneurContaining(String typeConteneur);
    Page<Conteneur> findByTypeConteneurContaining(String typeConteneur, Pageable pageable);
    List<Conteneur> findByTailleConteneurContaining(String tailleConteneur);
    Page<Conteneur> findByTailleConteneurContaining(String tailleConteneur, Pageable pageable);
    List<Conteneur> findByNumeroConteneurContainingOrTailleConteneurContainingOrTypeConteneurContaining(String numeroConteneur, String tailleConteneur, String typeConteneur);
    Page<Conteneur> findByNumeroConteneurContainingOrTailleConteneurContainingOrTypeConteneurContaining(String numeroConteneur, String tailleConteneur, String typeConteneur, Pageable pageable);
}
