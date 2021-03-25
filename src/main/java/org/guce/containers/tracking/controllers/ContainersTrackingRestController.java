/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.controllers;

import java.util.HashMap;
import java.util.Map;
import org.guce.containers.tracking.models.ContainerMovement;
import org.guce.containers.tracking.services.impl.ContainerMovementService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author penda
 */
@RestController
@RequestMapping("/api/v1")
public class ContainersTrackingRestController {

    @Autowired
    ContainerMovementService containerMovementService;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ContainersTrackingRestController.class);

    @GetMapping("/containers-movements/{id}")
    public Map<String, Object> getContainerMovement(@PathVariable("id") Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("item", containerMovementService.getContainerMovement(id));
        } catch (Exception ex) {
            logger.error(null, ex);
        }
        return result;
    }
}
