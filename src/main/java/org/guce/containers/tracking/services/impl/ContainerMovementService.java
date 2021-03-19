/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services.impl;

import java.util.List;
import org.guce.containers.tracking.models.ContainerMovement;
import org.guce.containers.tracking.repositories.ContainerMovementRepository;
import org.guce.containers.tracking.services.IContainerMovementService;
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
public class ContainerMovementService implements IContainerMovementService{
    
    @Autowired
    ContainerMovementRepository containerMovementRepository;

    @Override
    public List<ContainerMovement> findAll() {
        return containerMovementRepository.findAll();
    }

    @Transactional
    @Override
    public ContainerMovement save(ContainerMovement c) {
        return containerMovementRepository.save(c);
    }

    @Transactional
    @Override
    public ContainerMovement update(ContainerMovement c) {
        return containerMovementRepository.save(c);
    }

    @Transactional
    @Override
    public void delete(ContainerMovement c) {
        containerMovementRepository.delete(c);
    }

    @Override
    public ContainerMovement getContainerMovement(Long id) {
        return containerMovementRepository.getOne(id);
    }
    
}
