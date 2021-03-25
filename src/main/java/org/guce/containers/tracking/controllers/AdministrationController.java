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
import org.guce.containers.tracking.utils.containersTrackingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author penda
 */
@Controller
@RequestMapping("/admin")
public class AdministrationController {

    @Autowired
    ContainerMovementService containerMovementService;
    private static final Logger logger = LoggerFactory.getLogger(AdministrationController.class);

    @GetMapping({"", "/"})
    public ModelAndView homeAdmin(Model model) {
        ModelAndView mav = new ModelAndView("admin/index");
        mav.addObject("title", "ACCUEIL - CONTAINERS TRACKING");
        mav.addObject("view", "admin/home");
        mav.addObject("isHome", true);
        return mav;
    }

    /**
     * Cette function affiche la page de la liste des mouvements de conteneurs
     * enregistrer la plateforme e-GUCE
     *
     * @param model
     * @param allParams
     * @param httpServletRequest
     * @return ModelAndView
     */
    @GetMapping("/containers-movements")
    public ModelAndView homeRightsTaxesCategories(Model model, @RequestParam Map<String, String> allParams, HttpServletRequest httpServletRequest) {
        ModelAndView mav = new ModelAndView("admin/index");
        List<ContainerMovement> containersMovementsList;
        int page = 1;
        int size = 10;
        String orderBy = "movementDate";
        String containerNumber = "";
        String blNumber = "";
        String tripNumber = "";
        String link;
        int totalPages;
        int start;
        int end;
        try {
            link = httpServletRequest.getRequestURL().toString();
            if (allParams != null && !allParams.isEmpty()) {
                link = link + "?";
                if (allParams.containsKey("container-number") && allParams.get("container-number") != null && !allParams.get("container-number").equals("")) {
                    containerNumber = allParams.get("container-number");
                    link = link + "container-number=" + containerNumber + "&";
                }
                if (allParams.containsKey("bl-number") && allParams.get("bl-number") != null && !allParams.get("bl-number").equals("")) {
                    blNumber = allParams.get("bl-number");
                    link = link + "bl-number=" + blNumber + "&";
                }
                if (allParams.containsKey("trip-number") && allParams.get("trip-number") != null && !allParams.get("trip-number").equals("")) {
                    tripNumber = allParams.get("trip-number");
                    link = link + "trip-number=" + tripNumber + "&";
                }
                if (allParams.containsKey("page") && allParams.get("page") != null && !allParams.get("page").equals("")) {
                    page = Integer.parseInt(allParams.get("page"));
                }
                if (allParams.containsKey("size") && allParams.get("size") != null && !allParams.get("size").equals("")) {
                    size = Integer.parseInt(allParams.get("size"));
                    link = link + "size=" + size + "&";
                }
                if (allParams.containsKey("order-by") && allParams.get("order-by") != null && !allParams.get("order-by").equals("")) {
                    orderBy = allParams.get("order-by");
                    link = link + "orderby-by=" + orderBy + "&";
                }
                if (link.lastIndexOf("&") > 0) {
                    link = link.substring(0, link.lastIndexOf("&"));
                }
            }
            Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.ASC, orderBy);
            Page<ContainerMovement> pageableContainerMovement = containerMovementService.findByContainerNumberContainingOrBlNumberContainingOrTripNumberContaining(containerNumber, blNumber, tripNumber, pageable);
            containersMovementsList = pageableContainerMovement.getContent();
            totalPages = pageableContainerMovement.getTotalPages();
            start = 1;
            end = totalPages;
            if (totalPages > 1) {
                if (totalPages > 5 && page > 3) {
                    end = page + 2 < totalPages ? page + 2 : totalPages;
                    start = end - 4 > 1 ? end - 4 : 1;
                } else if (totalPages > 5) {
                    end = 5;
                }
            }
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return null;
        }
        mav.addObject("title", "MOUVEMENTS DE CONTENEURS - CONTAINERS TRACKING");
        mav.addObject("view", "/admin/movements/index");
        mav.addObject("isHome", false);
        mav.addObject("page", page);
        mav.addObject("size", size);
        mav.addObject("totalPages", totalPages);
        mav.addObject("start", start);
        mav.addObject("end", end);
        mav.addObject("order-by", orderBy);
        mav.addObject("isContainerMovements", true);
        mav.addObject("containerMovementList", containersMovementsList);
        mav.addObject("containerNumber", containerNumber);
        mav.addObject("blNumber", blNumber);
        mav.addObject("tripNumber", tripNumber);
        mav.addObject("link", link);
        Boolean containersTrackingResponseSuccess = (Boolean) httpServletRequest.getSession().getAttribute("containersTrackingResponseSuccess");
        String containersTrackingResponseMessage = (String) httpServletRequest.getSession().getAttribute("containersTrackingResponseMessage");
        if (containersTrackingResponseSuccess != null) {
            logger.info("containersTrackingResponseSuccess is in session");
            mav.addObject("containersTrackingResponseSuccess", containersTrackingResponseSuccess);
            httpServletRequest.getSession().removeAttribute("containersTrackingResponseSuccess");
        }
        if (containersTrackingResponseMessage != null) {
            logger.info("containersTrackingResponseMessage is in session");
            mav.addObject("containersTrackingResponseMessage", containersTrackingResponseMessage);
            httpServletRequest.getSession().removeAttribute("containersTrackingResponseMessage");
        }
        return mav;

    }
    
    /**
     * Cette function affiche le formulaire d'importation des mouvements de conteneurs
     *
     * @param request
     * @param model
     * @return ModelAndView
     */
    @GetMapping("/containers-movements/import-data")
    public ModelAndView getImportContainersMovementsForm(Model model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/index");
        mav.addObject("title", "IMPORTER MOUVEMENTS CONTENEURS - CONTAINERS TRACKING");
        mav.addObject("view", "/admin/movements/import-containers-movements");
        mav.addObject("isHome", false);
        mav.addObject("isContainersMovements", true);
        mav.addObject("isOneContainerMovement", true);
        Boolean containersTrackingResponseSuccess = (Boolean) request.getSession().getAttribute("containersTrackingResponseSuccess");
        String containersTrackingResponseMessage = (String) request.getSession().getAttribute("containersTrackingResponseMessage");
        if (containersTrackingResponseSuccess != null) {
            logger.info("containersTrackingResponseSuccess is in session");
            mav.addObject("containersTrackingResponseSuccess", containersTrackingResponseSuccess);
            request.getSession().removeAttribute("containersTrackingResponseSuccess");
        }
        if (containersTrackingResponseMessage != null) {
            logger.info("containersTrackingResponseMessage is in session");
            mav.addObject("containersTrackingResponseMessage", containersTrackingResponseMessage);
            request.getSession().removeAttribute("containersTrackingResponseMessage");
        }

        return mav;
    }
    
    @PostMapping("/containers-movements/import-data")
    public ModelAndView postImportContainersMovementData(@RequestParam("file") MultipartFile file, @RequestParam Map<String, String> allParams, HttpServletRequest httpServletRequest) {
        ModelAndView referenceTableListView = new ModelAndView("redirect:/admin/containers-movements");
        try {
            String filename = file.getName();
            List<ContainerMovement> containerMovementList = containersTrackingUtils.getContainersMovementsFromFile(file.getInputStream());
            if (containerMovementList != null && !containerMovementList.isEmpty()) {
                for (ContainerMovement containerMovement : containerMovementList) {
                    logger.info("Container Movement : " + containerMovement.toString());
                    
                    containerMovementService.save(containerMovement);
                }
            }

            httpServletRequest.getSession().setAttribute("containersTrackingResponseSuccess", true);
            httpServletRequest.getSession().setAttribute("containersTrackingResponseMessage", "Les mouvements de conteneurs ont été importés avec succès !");
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            httpServletRequest.getSession().setAttribute("containersTrackingResponseSuccess", false);
            httpServletRequest.getSession().setAttribute("containersTrackingResponseMessage", ex.getMessage());
        }
        return referenceTableListView;
    }
    
    @GetMapping("/containers-movements/delete/{id}")
    public ModelAndView deleteRightTaxeCategory(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        ModelAndView containersMovementsListView = new ModelAndView("redirect:/admin/containers-movements");
        try {
            ContainerMovement containerMovement = containerMovementService.getContainerMovement(id);
            containerMovementService.delete(containerMovement);
            request.getSession().setAttribute("containersTrackingResponseSuccess", true);
            request.getSession().setAttribute("containersTrackingResponseMessage", "Le mouvement du conteneur a été supprimée !");
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            request.getSession().setAttribute("containersTrackingResponseSuccess", false);
            request.getSession().setAttribute("containersTrackingResponseMessage", "Une erreur s'est produite lors de la suppression du mouvement !");
        }
        return containersMovementsListView;
    }
}
