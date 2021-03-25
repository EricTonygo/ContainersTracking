/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services;

import java.util.List;
import org.guce.containers.tracking.models.ContainerMovement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author penda
 */
public interface IContainerMovementService {
    public List<ContainerMovement> findAll();
    public ContainerMovement save(ContainerMovement c);
    public ContainerMovement update(ContainerMovement c);
    public void delete(ContainerMovement c);
    public ContainerMovement getContainerMovement(Long id);
    public List<ContainerMovement> findByContainerNumber(String containerNumber);
    public List<ContainerMovement> findByContainerNumberContaining(String containerNumber);
    public Page<ContainerMovement> findByContainerNumberContaining(String containerNumber, Pageable pageable);
    public List<ContainerMovement> findByBlNumber(String blNumber);
    public List<ContainerMovement> findByBlNumberContaining(String blNumber);
    public Page<ContainerMovement> findByBlNumberContaining(String blNumber, Pageable pageable);
    public List<ContainerMovement> findByTripNumber(String tripNumber);
    public List<ContainerMovement> findByTripNumberContaining(String tripNumber);
    public Page<ContainerMovement> findByTripNumberContaining(String tripNumber, Pageable pageable);
    public Page<ContainerMovement> findByContainerNumberContainingOrBlNumberContainingOrTripNumberContaining(String containerNumber, String blNumber, String tripNumber, Pageable pageable);
    public List<ContainerMovement> findByContainerNumberContainingOrBlNumberContainingOrTripNumberContaining(String containerNumber, String blNumber, String tripNumber);
}
