/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.controllers;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.guce.containers.tracking.models.MouvementConteneur;
import org.guce.containers.tracking.services.impl.MouvementConteneurService;
import org.guce.containers.tracking.utils.containersTrackingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author penda
 */
@Controller
public class HomeController {
    
    @Autowired
    MouvementConteneurService containerMovementService;
    @Autowired
    ResourceLoader resourceLoader;
    
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
        List<MouvementConteneur> containersMovements  =  null;
        MouvementConteneur containerMovementSearch = null;
        try{
            if (allParams != null && !allParams.isEmpty()) {
                if (allParams.containsKey("search-number") && allParams.get("search-number") != null && !allParams.get("search-number").equals("")) {
                    searchNumber = allParams.get("search-number");
                    containersMovements = containerMovementService.findByNumeroConteneurContainingOrNumeroBLContainingOrNumeroVoyageContaining(searchNumber, searchNumber, searchNumber);
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
    
    @GetMapping("/search-container-movement/{id}/export-to-pdf")
    public ResponseEntity<byte[]> exporterMouvementConteneurEnPDF(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        try {
            
            MouvementConteneur containerMovement = containerMovementService.getContainerMovement(id);
            byte[] contents = containersTrackingUtils.genererRapportRechercheMouvementConteneur(containerMovement, resourceLoader);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            // Here you have to set the actual filename of your pdf
            String filename = "resultats_recherche_conteneur_" + containerMovement.getNumeroConteneur()+ ".pdf";
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
            return response;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        }

    }
}
