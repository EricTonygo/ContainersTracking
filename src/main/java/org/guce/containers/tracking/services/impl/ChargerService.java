/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services.impl;

import java.util.List;
import org.guce.containers.tracking.models.Charger;
import org.guce.containers.tracking.repositories.ChargerRepository;
import org.guce.containers.tracking.services.IChargerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author penda
 */
@Service
@Component
public class ChargerService implements IChargerService{
    
    @Autowired
    ChargerRepository chargerRepository;

    @Override
    public List<Charger> findAll() {
        return chargerRepository.findAll();
    }

    @Transactional
    @Override
    public Charger save(Charger c) {
        return chargerRepository.save(c);
    }

    @Transactional
    @Override
    public Charger update(Charger c) {
        return chargerRepository.save(c);
    }

    @Transactional
    @Override
    public void delete(Charger c) {
        chargerRepository.delete(c);
    }

    @Override
    public Charger getCharger(Long id) {
        return chargerRepository.getOne(id);
    }
    
}
