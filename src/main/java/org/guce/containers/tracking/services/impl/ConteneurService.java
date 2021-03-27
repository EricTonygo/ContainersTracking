/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services.impl;

import java.util.List;
import org.guce.containers.tracking.models.Conteneur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.guce.containers.tracking.repositories.ConteneurRepository;
import org.guce.containers.tracking.services.IConteneurService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @author penda
 */
@Service
@Component
public class ConteneurService implements IConteneurService{

    @Autowired
    ConteneurRepository conteneurRepository;
    
    @Override
    public List<Conteneur> findAll() {
        return conteneurRepository.findAll();
    }

    @Transactional
    @Override
    public Conteneur save(Conteneur c) {
        return conteneurRepository.save(c);
    }

    @Transactional
    @Override
    public Conteneur update(Conteneur c) {
        return conteneurRepository.save(c);
    }

    @Transactional
    @Override
    public void delete(Conteneur c) {
        conteneurRepository.delete(c);
    }

    @Override
    public Conteneur getContainer(Long id) {
        return conteneurRepository.getOne(id);
    }

    @Override
    public Conteneur findByNumeroConteneur(String numeroConteneur) {
        return conteneurRepository.findByNumeroConteneur(numeroConteneur);
    }

    @Override
    public List<Conteneur> findByTypeConteneurContaining(String typeConteneur) {
        return conteneurRepository.findByTypeConteneurContaining(typeConteneur, Sort.by(Sort.Direction.ASC, "numeroConteneur"));
    }

    @Override
    public Page<Conteneur> findByTypeConteneurContaining(String typeConteneur, Pageable pageable) {
        return conteneurRepository.findByTypeConteneurContaining(typeConteneur, pageable);
    }

    @Override
    public List<Conteneur> findByTailleConteneurContaining(String tailleConteneur) {
        return conteneurRepository.findByTailleConteneurContaining(tailleConteneur, Sort.by(Sort.Direction.ASC, "numeroConteneur"));
    }

    @Override
    public Page<Conteneur> findByTailleConteneurContaining(String tailleConteneur, Pageable pageable) {
        return conteneurRepository.findByTailleConteneurContaining(tailleConteneur, pageable);
    }

    @Override
    public List<Conteneur> findByNumeroConteneurContainingOrTailleConteneurContainingOrTypeConteneurContaining(String numeroConteneur, String tailleConteneur, String typeConteneur) {
        return conteneurRepository.findByNumeroConteneurContainingOrTailleConteneurContainingOrTypeConteneurContaining(numeroConteneur, tailleConteneur, typeConteneur, Sort.by(Sort.Direction.ASC, "numeroConteneur"));
    }

    @Override
    public Page<Conteneur> findByNumeroConteneurContainingOrTailleConteneurContainingOrTypeConteneurContaining(String numeroConteneur, String tailleConteneur, String typeConteneur, Pageable pageable) {
        return conteneurRepository.findByNumeroConteneurContainingOrTailleConteneurContainingOrTypeConteneurContaining(numeroConteneur, tailleConteneur, typeConteneur, pageable);
    }
    
}
