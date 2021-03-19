/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.services;

import java.util.List;
import org.guce.containers.tracking.models.ContainerMovement;

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
}
