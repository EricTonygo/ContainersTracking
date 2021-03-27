/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services.impl;

import java.util.Arrays;
import java.util.List;
import org.guce.containers.tracking.models.MouvementConteneur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.guce.containers.tracking.repositories.MouvementConteneurRepository;
import org.guce.containers.tracking.services.IMouvementConteneurService;

/**
 *
 * @author penda
 */
@Service
@Component
public class MouvementConteneurService implements IMouvementConteneurService{
    
    @Autowired
    MouvementConteneurRepository mouvementConteneurRepository;

    @Override
    public List<MouvementConteneur> findAll() {
        return mouvementConteneurRepository.findAll();
    }

    @Transactional
    @Override
    public MouvementConteneur save(MouvementConteneur c) {
        return mouvementConteneurRepository.save(c);
    }

    @Transactional
    @Override
    public MouvementConteneur update(MouvementConteneur c) {
        return mouvementConteneurRepository.save(c);
    }

    @Transactional
    @Override
    public void delete(MouvementConteneur c) {
        mouvementConteneurRepository.delete(c);
    }

    @Override
    public MouvementConteneur getContainerMovement(Long id) {
        return mouvementConteneurRepository.getOne(id);
    }

    @Override
    public List<MouvementConteneur> findByNumeroConteneur(String numeroConteneur) {
        return (List<MouvementConteneur>)mouvementConteneurRepository.findByNumeroBL(numeroConteneur, Sort.by(Sort.Direction.DESC, "dateMouvement"));
    }

    @Override
    public List<MouvementConteneur> findByNumeroConteneurContaining(String numeroConteneur) {
        return (List<MouvementConteneur>) mouvementConteneurRepository.findByNumeroBLContaining(numeroConteneur, Sort.by(Sort.Direction.DESC, "dateMouvement"));
    }

    @Override
    public Page<MouvementConteneur> findByNumeroConteneurContaining(String numeroConteneur, Pageable pageable) {
        return mouvementConteneurRepository.findByNumeroConteneurContaining(numeroConteneur, pageable);
    }

    @Override
    public List<MouvementConteneur> findByNumeroBL(String numeroBL) {
        return mouvementConteneurRepository.findByNumeroBL(numeroBL, Sort.by(Sort.Direction.DESC, "dateMouvement"));
    }

    @Override
    public List<MouvementConteneur> findByNumeroBLContaining(String numeroBL) {
        return mouvementConteneurRepository.findByNumeroBLContaining(numeroBL, Sort.by(Sort.Direction.DESC, "dateMouvement"));
    }

    @Override
    public Page<MouvementConteneur> findByNumeroBLContaining(String numeroBL, Pageable pageable) {
        return mouvementConteneurRepository.findByNumeroBLContaining(numeroBL, pageable);
    }

    @Override
    public List<MouvementConteneur> findByNumeroVoyage(String numeroVoyage) {
        return mouvementConteneurRepository.findByNumeroVoyage(numeroVoyage, Sort.by(Sort.Direction.DESC, "dateMouvement"));
    }

    @Override
    public List<MouvementConteneur> findByNumeroVoyageContaining(String numeroVoyage) {
        return mouvementConteneurRepository.findByNumeroBLContaining(numeroVoyage, Sort.by(Sort.Direction.DESC, "dateMouvement"));
    }

    @Override
    public Page<MouvementConteneur> findByNumeroVoyageContaining(String numeroVoyage, Pageable pageable) {
        return mouvementConteneurRepository.findByNumeroVoyageContaining(numeroVoyage, pageable);
    }

    @Override
    public Page<MouvementConteneur> findByNumeroConteneurContainingOrNumeroBLContainingOrNumeroVoyageContaining(String numeroConteneur, String numeroBL, String numeroVoyage, Pageable pageable) {
        return mouvementConteneurRepository.findByNumeroConteneurContainingOrNumeroBLContainingOrNumeroVoyageContaining(numeroConteneur, numeroBL, numeroVoyage, pageable);
    }

    @Override
    public List<MouvementConteneur> findByNumeroConteneurContainingOrNumeroBLContainingOrNumeroVoyageContaining(String numeroConteneur, String numeroBL, String numeroVoyage) {
        return mouvementConteneurRepository.findByNumeroConteneurContainingOrNumeroBLContainingOrNumeroVoyageContaining(numeroConteneur, numeroBL, numeroVoyage, Sort.by(Sort.Direction.DESC, "dateMouvement"));
    }
    
}
