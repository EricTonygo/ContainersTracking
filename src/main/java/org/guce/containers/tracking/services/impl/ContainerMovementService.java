/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services.impl;

import java.util.Arrays;
import java.util.List;
import org.guce.containers.tracking.models.ContainerMovement;
import org.guce.containers.tracking.repositories.ContainerMovementRepository;
import org.guce.containers.tracking.services.IContainerMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public List<ContainerMovement> findByContainerNumber(String containerNumber) {
        return (List<ContainerMovement>)containerMovementRepository.findByBlNumber(containerNumber, Sort.by(Sort.Direction.DESC, "movementDate"));
    }

    @Override
    public List<ContainerMovement> findByContainerNumberContaining(String containerNumber) {
        return (List<ContainerMovement>) containerMovementRepository.findByBlNumberContaining(containerNumber, Sort.by(Sort.Direction.DESC, "movementDate"));
    }

    @Override
    public Page<ContainerMovement> findByContainerNumberContaining(String containerNumber, Pageable pageable) {
        return containerMovementRepository.findByContainerNumberContaining(containerNumber, pageable);
    }

    @Override
    public List<ContainerMovement> findByBlNumber(String blNumber) {
        return containerMovementRepository.findByBlNumber(blNumber, Sort.by(Sort.Direction.DESC, "movementDate"));
    }

    @Override
    public List<ContainerMovement> findByBlNumberContaining(String blNumber) {
        return containerMovementRepository.findByBlNumberContaining(blNumber, Sort.by(Sort.Direction.DESC, "movementDate"));
    }

    @Override
    public Page<ContainerMovement> findByBlNumberContaining(String blNumber, Pageable pageable) {
        return containerMovementRepository.findByBlNumberContaining(blNumber, pageable);
    }

    @Override
    public List<ContainerMovement> findByTripNumber(String tripNumber) {
        return containerMovementRepository.findByTripNumber(tripNumber, Sort.by(Sort.Direction.DESC, "movementDate"));
    }

    @Override
    public List<ContainerMovement> findByTripNumberContaining(String tripNumber) {
        return containerMovementRepository.findByBlNumberContaining(tripNumber, Sort.by(Sort.Direction.DESC, "movementDate"));
    }

    @Override
    public Page<ContainerMovement> findByTripNumberContaining(String tripNumber, Pageable pageable) {
        return containerMovementRepository.findByTripNumberContaining(tripNumber, pageable);
    }

    @Override
    public Page<ContainerMovement> findByContainerNumberContainingOrBlNumberContainingOrTripNumberContaining(String containerNumber, String blNumber, String tripNumber, Pageable pageable) {
        return containerMovementRepository.findByContainerNumberContainingOrBlNumberContainingOrTripNumberContaining(containerNumber, blNumber, tripNumber, pageable);
    }

    @Override
    public List<ContainerMovement> findByContainerNumberContainingOrBlNumberContainingOrTripNumberContaining(String containerNumber, String blNumber, String tripNumber) {
        return containerMovementRepository.findByContainerNumberContainingOrBlNumberContainingOrTripNumberContaining(containerNumber, blNumber, tripNumber, Sort.by(Sort.Direction.DESC, "movementDate"));
    }
    
}
