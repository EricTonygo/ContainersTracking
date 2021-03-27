/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.controllers;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.guce.containers.tracking.models.Conteneur;
import org.guce.containers.tracking.models.HistoriqueRechercheConteneur;
import org.guce.containers.tracking.models.MouvementConteneur;
import org.guce.containers.tracking.models.Utilisateur;
import org.guce.containers.tracking.services.impl.ConteneurService;
import org.guce.containers.tracking.services.impl.HistoriqueRechercheConteneurService;
import org.guce.containers.tracking.services.impl.MouvementConteneurService;
import org.guce.containers.tracking.services.impl.UtilisateurService;
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
    MouvementConteneurService containerMovementService;
    
    @Autowired
    ConteneurService conteneurService;
    @Autowired
    HistoriqueRechercheConteneurService historiqueRechercheConteneurService;
    @Autowired
    UtilisateurService utilisateurService;
    
    private static final Logger logger = LoggerFactory.getLogger(AdministrationController.class);

    @GetMapping({"", "/"})
    public ModelAndView homeAdmin(Model model) {
        ModelAndView mav = new ModelAndView("admin/index");
        List<MouvementConteneur> mouvementConteneurs = containerMovementService.findAll();
        List<Conteneur> conteneurs = conteneurService.findAll();
        List<HistoriqueRechercheConteneur> historiqueRechercheConteneurs = historiqueRechercheConteneurService.findAll();
        List<Utilisateur> utilisateurs = utilisateurService.findAll();
        int mouvementConteneursSize = mouvementConteneurs != null ? mouvementConteneurs.size() : 0;
        int conteneursSize = conteneurs != null ? conteneurs.size() : 0;
        int historiqueRechercheConteneursSize = historiqueRechercheConteneurs != null ? historiqueRechercheConteneurs.size() : 0;
        int utilisateursSize = utilisateurs != null ? utilisateurs.size() : 0;
        mav.addObject("title", "ACCUEIL - CONTAINERS TRACKING");
        mav.addObject("view", "admin/home");
        mav.addObject("isHome", true);
        mav.addObject("mouvementConteneursSize", mouvementConteneursSize);
        mav.addObject("conteneursSize", conteneursSize);
        mav.addObject("historiqueRechercheConteneursSize", historiqueRechercheConteneursSize);
        mav.addObject("utilisateursSize", utilisateursSize);
        return mav;
    }

    /**
     * Cette function affiche la page de la liste des mouvements de conteneurs
     * enregistrer la plateforme Containers Tracking
     *
     * @param model
     * @param allParams
     * @param httpServletRequest
     * @return ModelAndView
     */
    @GetMapping("/containers-movements")
    public ModelAndView homeMouvementsConteneurs(Model model, @RequestParam Map<String, String> allParams, HttpServletRequest httpServletRequest) {
        ModelAndView mav = new ModelAndView("admin/index");
        List<MouvementConteneur> containersMovementsList;
        int page = 1;
        int size = 10;
        String orderBy = "dateMouvement";
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
            Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, orderBy);
            Page<MouvementConteneur> pageableContainerMovement = containerMovementService.findByNumeroConteneurContainingOrNumeroBLContainingOrNumeroVoyageContaining(containerNumber, blNumber, tripNumber, pageable);
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
     * Cette function affiche la page de la liste des conteneurs
     * enregistrer la plateforme Containers Tracking
     *
     * @param model
     * @param allParams
     * @param httpServletRequest
     * @return ModelAndView
     */
    @GetMapping("/containers")
    public ModelAndView homeConteneurs(Model model, @RequestParam Map<String, String> allParams, HttpServletRequest httpServletRequest) {
        ModelAndView mav = new ModelAndView("admin/index");
        List<Conteneur> conteneursList;
        int page = 1;
        int size = 10;
        String orderBy = "numeroConteneur";
        String containerNumber = "";
        String containerType = "";
        String containerSize = "";
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
                if (allParams.containsKey("container-type") && allParams.get("container-type") != null && !allParams.get("container-type").equals("")) {
                    containerType = allParams.get("container-type");
                    link = link + "container-type=" + containerType + "&";
                }
                if (allParams.containsKey("container-size") && allParams.get("container-size") != null && !allParams.get("container-size").equals("")) {
                    containerSize = allParams.get("container-size");
                    link = link + "container-size=" + containerSize + "&";
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
            Page<Conteneur> pageableConteneurs= conteneurService.findByNumeroConteneurContainingOrTailleConteneurContainingOrTypeConteneurContaining(containerNumber, containerSize, containerType, pageable);
            conteneursList = pageableConteneurs.getContent();
            totalPages = pageableConteneurs.getTotalPages();
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
        mav.addObject("title", "CONTENEURS - CONTAINERS TRACKING");
        mav.addObject("view", "/admin/conteneurs/index");
        mav.addObject("isHome", false);
        mav.addObject("page", page);
        mav.addObject("size", size);
        mav.addObject("totalPages", totalPages);
        mav.addObject("start", start);
        mav.addObject("end", end);
        mav.addObject("order-by", orderBy);
        mav.addObject("isConteneurs", true);
        mav.addObject("conteneurs", conteneursList);
        mav.addObject("containerNumber", containerNumber);
        mav.addObject("containerSize", containerSize);
        mav.addObject("containerType", containerType);
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
     * Cette function affiche la page de la liste des conteneurs
     * enregistrer la plateforme Containers Tracking
     *
     * @param model
     * @param allParams
     * @param httpServletRequest
     * @return ModelAndView
     */
    @GetMapping("/search-containers-historic")
    public ModelAndView homeHistoriqueRecherchesConteneurs(Model model, @RequestParam Map<String, String> allParams, HttpServletRequest httpServletRequest) {
        ModelAndView mav = new ModelAndView("admin/index");
        List<HistoriqueRechercheConteneur> historiqueRecherchesConteneursList;
        int page = 1;
        int size = 10;
        String orderBy = "idHistorique";
        String link;
        int totalPages;
        int start;
        int end;
        try {
            link = httpServletRequest.getRequestURL().toString();
            if (allParams != null && !allParams.isEmpty()) {
                link = link + "?";
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
            Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, orderBy);
            Page<HistoriqueRechercheConteneur> pageableHistoriques= historiqueRechercheConteneurService.findAll(pageable);
            historiqueRecherchesConteneursList = pageableHistoriques.getContent();
            totalPages = pageableHistoriques.getTotalPages();
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
        mav.addObject("title", "HISTORIQUE RECHERCHES CONTENEURS - CONTAINERS TRACKING");
        mav.addObject("view", "/admin/historique-recherches-conteneurs/index");
        mav.addObject("isHome", false);
        mav.addObject("page", page);
        mav.addObject("size", size);
        mav.addObject("totalPages", totalPages);
        mav.addObject("start", start);
        mav.addObject("end", end);
        mav.addObject("order-by", orderBy);
        mav.addObject("isHistoriqueRecherchesConteneurs", true);
        mav.addObject("historiqueRecherchesConteneurs", historiqueRecherchesConteneursList);
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
     * Cette function affiche la page de la liste des utilisateurs
     * enregistrer la plateforme Containers Tracking
     *
     * @param model
     * @param allParams
     * @param httpServletRequest
     * @return ModelAndView
     */
    @GetMapping("/users")
    public ModelAndView homeUtilisateurs(Model model, @RequestParam Map<String, String> allParams, HttpServletRequest httpServletRequest) {
        ModelAndView mav = new ModelAndView("admin/index");
        List<Utilisateur> utilisateursList;
        int page = 1;
        int size = 10;
        String orderBy = "nom";
        String email = "";
        String nom = "";
        String prenom = "";
        String link;
        int totalPages;
        int start;
        int end;
        try {
            link = httpServletRequest.getRequestURL().toString();
            if (allParams != null && !allParams.isEmpty()) {
                link = link + "?";
                if (allParams.containsKey("email") && allParams.get("email") != null && !allParams.get("email").equals("")) {
                    email = allParams.get("email");
                    link = link + "email=" + email + "&";
                }
                if (allParams.containsKey("nom") && allParams.get("nom") != null && !allParams.get("nom").equals("")) {
                    nom = allParams.get("nom");
                    link = link + "nom=" + nom + "&";
                }
                if (allParams.containsKey("prenom") && allParams.get("prenom") != null && !allParams.get("prenom").equals("")) {
                    prenom = allParams.get("prenom");
                    link = link + "prenom=" + prenom + "&";
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
            Page<Utilisateur> pageableUtilisateurs= utilisateurService.findByEmailContainingOrNomContainingOrPrenomContaining(email, nom, prenom, pageable);
            utilisateursList = pageableUtilisateurs.getContent();
            totalPages = pageableUtilisateurs.getTotalPages();
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
        mav.addObject("title", "UTILISATEURS - CONTAINERS TRACKING");
        mav.addObject("view", "/admin/utilisateurs/index");
        mav.addObject("isHome", false);
        mav.addObject("page", page);
        mav.addObject("size", size);
        mav.addObject("totalPages", totalPages);
        mav.addObject("start", start);
        mav.addObject("end", end);
        mav.addObject("order-by", orderBy);
        mav.addObject("isUtilisateurs", true);
        mav.addObject("utilisateurs", utilisateursList);
        mav.addObject("email", email);
        mav.addObject("nom", nom);
        mav.addObject("prenom", prenom);
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
            List<MouvementConteneur> containerMovementList = containersTrackingUtils.getContainersMovementsFromFile(file.getInputStream());
            if (containerMovementList != null && !containerMovementList.isEmpty()) {
                for (MouvementConteneur containerMovement : containerMovementList) {
                    logger.info("Container Movement : " + containerMovement.toString());
                    if(containerMovement.getNumeroVoyage() != null && !containerMovement.getNumeroConteneur().equals("")){
                        Conteneur existConteneur = conteneurService.findByNumeroConteneur(containerMovement.getNumeroConteneur());
                        if(existConteneur != null){
                            containerMovement.setIdConteneur(existConteneur);
                        }else{
                            Conteneur nouveauConteneur = new Conteneur();
                            nouveauConteneur.setNumeroConteneur(containerMovement.getNumeroConteneur());
                            nouveauConteneur.setTailleConteneur(containerMovement.getTailleConteneur());
                            nouveauConteneur.setTypeConteneur(containerMovement.getTypeConteneur());
                            nouveauConteneur = conteneurService.save(nouveauConteneur);
                            containerMovement.setIdConteneur(nouveauConteneur);
                        }
                    }
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
            MouvementConteneur containerMovement = containerMovementService.getContainerMovement(id);
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
