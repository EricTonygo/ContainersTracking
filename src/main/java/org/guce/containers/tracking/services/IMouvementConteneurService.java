/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services;

import java.util.List;
import org.guce.containers.tracking.models.MouvementConteneur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author penda
 */
public interface IMouvementConteneurService {
    public List<MouvementConteneur> findAll();
    public MouvementConteneur save(MouvementConteneur c);
    public MouvementConteneur update(MouvementConteneur c);
    public void delete(MouvementConteneur c);
    public MouvementConteneur getContainerMovement(Long id);
    public List<MouvementConteneur> findByNumeroConteneur(String numeroConteneur);
    public List<MouvementConteneur> findByNumeroConteneurContaining(String numeroConteneur);
    public Page<MouvementConteneur> findByNumeroConteneurContaining(String numeroConteneur, Pageable pageable);
    public List<MouvementConteneur> findByNumeroBL(String numeroBL);
    public List<MouvementConteneur> findByNumeroBLContaining(String numeroBL);
    public Page<MouvementConteneur> findByNumeroBLContaining(String numeroBL, Pageable pageable);
    public List<MouvementConteneur> findByNumeroVoyage(String numeroVoyage);
    public List<MouvementConteneur> findByNumeroVoyageContaining(String numeroVoyage);
    public Page<MouvementConteneur> findByNumeroVoyageContaining(String numeroVoyage, Pageable pageable);
    public Page<MouvementConteneur> findByNumeroConteneurContainingOrNumeroBLContainingOrNumeroVoyageContaining(String numeroConteneur, String numeroBL, String numeroVoyage, Pageable pageable);
    public List<MouvementConteneur> findByNumeroConteneurContainingOrNumeroBLContainingOrNumeroVoyageContaining(String numeroConteneur, String numeroBL, String numeroVoyage);
}
