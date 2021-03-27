/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services.impl;

import java.util.List;
import org.guce.containers.tracking.models.HistoriqueRechercheConteneur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.guce.containers.tracking.repositories.HistoriqueRechercheConteneurRepository;
import org.guce.containers.tracking.services.IHistoriqueRechercheConteneurService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author penda
 */
@Service
@Component
public class HistoriqueRechercheConteneurService implements IHistoriqueRechercheConteneurService {
    
    @Autowired
    HistoriqueRechercheConteneurRepository userContainerSearchRepository;

    @Override
    public List<HistoriqueRechercheConteneur> findAll() {
        return userContainerSearchRepository.findAll();
    }

    @Transactional
    @Override
    public HistoriqueRechercheConteneur save(HistoriqueRechercheConteneur c) {
        return userContainerSearchRepository.save(c);
    }

    @Transactional
    @Override
    public HistoriqueRechercheConteneur update(HistoriqueRechercheConteneur c) {
        return userContainerSearchRepository.save(c);
    }

    @Transactional
    @Override
    public void delete(HistoriqueRechercheConteneur c) {
        userContainerSearchRepository.delete(c);
    }

    @Override
    public HistoriqueRechercheConteneur getUserContainerSearch(Long id) {
        return userContainerSearchRepository.getOne(id);
    }

    @Override
    public Page<HistoriqueRechercheConteneur> findAll(Pageable pageable) {
        return userContainerSearchRepository.findAll(pageable);
    }
    
}
