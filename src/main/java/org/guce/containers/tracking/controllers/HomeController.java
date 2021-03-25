/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.controllers;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.guce.containers.tracking.models.ContainerMovement;
import org.guce.containers.tracking.services.impl.ContainerMovementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author penda
 */
@Controller
public class HomeController {
    
    @Autowired
    ContainerMovementService containerMovementService;
    private static final Logger logger = LoggerFactory.getLogger(AdministrationController.class);
    @GetMapping({"", "/"})
    public ModelAndView homeContainersTracking(Model model) {
        ModelAndView mav = new ModelAndView("home/index");
        mav.addObject("title", "ACCUEIL - CONTAINERS TRACKING");
        mav.addObject("view", "home/home");
        mav.addObject("isHome", true);
        return mav;
    }
    
    /**
     * Cette function affiche toutes les catégorisations des droits et taxes
     *
     * @param model
     * @param allParams
     * @param httpServletRequest
     * @return ModelAndView
     */
    @GetMapping("/search-container-movement")
    public ModelAndView homeRightsTaxesCategories(Model model, @RequestParam Map<String, String> allParams, HttpServletRequest httpServletRequest) {
        ModelAndView mav = new ModelAndView("home/index");
        String searchNumber = "";
        List<ContainerMovement> containersMovements  =  null;
        ContainerMovement containerMovementSearch = null;
        try{
            if (allParams != null && !allParams.isEmpty()) {
                if (allParams.containsKey("search-number") && allParams.get("search-number") != null && !allParams.get("search-number").equals("")) {
                    searchNumber = allParams.get("search-number");
                    containersMovements = containerMovementService.findByContainerNumberContainingOrBlNumberContainingOrTripNumberContaining(searchNumber, searchNumber, searchNumber);
                    if(containersMovements != null && !containersMovements.isEmpty()){
                        containerMovementSearch = containersMovements.get(0);
                    }
                }
            }
        }catch(Exception ex){
            logger.warn(ex.getMessage(), ex);
        }
        mav.addObject("title", "RECHERCHER MOUVEMENTS CONTENEURS - CONTAINERS TRACKING");
        mav.addObject("view", "home/container-movement-search");
        mav.addObject("isHome", true);
        mav.addObject("searchNumber", searchNumber);
        mav.addObject("containerMovementSearch", containerMovementSearch);
        mav.addObject("isContainerMovementSearch", true);
        return mav;
    }
}
