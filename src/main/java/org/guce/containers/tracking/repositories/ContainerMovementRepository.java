/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.repositories;

import java.util.List;
import org.guce.containers.tracking.models.ContainerMovement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author penda
 */
@Repository
public interface ContainerMovementRepository extends JpaRepository<ContainerMovement, Long>{
    List<ContainerMovement> findByContainerNumber(String containerNumber, Sort sort);
    List<ContainerMovement> findByContainerNumberContaining(String containerNumber, Sort sort);
    Page<ContainerMovement> findByContainerNumberContaining(String containerNumber, Pageable pageable);
    List<ContainerMovement> findByBlNumber(String blNumber, Sort sort);
    List<ContainerMovement> findByBlNumberContaining(String blNumber, Sort sort);
    Page<ContainerMovement> findByBlNumberContaining(String blNumber, Pageable pageable);
    List<ContainerMovement> findByTripNumber(String tripNumber, Sort sort);
    List<ContainerMovement> findByTripNumberContaining(String tripNumber, Sort sort);
    Page<ContainerMovement> findByTripNumberContaining(String tripNumber, Pageable pageable);
    Page<ContainerMovement> findByContainerNumberContainingOrBlNumberContainingOrTripNumberContaining(String containerNumber, String blNumber, String tripNumber, Pageable pageable);
    List<ContainerMovement> findByContainerNumberContainingOrBlNumberContainingOrTripNumberContaining(String containerNumber, String blNumber, String tripNumber, Sort sort);
}
