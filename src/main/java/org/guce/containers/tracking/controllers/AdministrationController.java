/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author penda
 */
@Controller
@RequestMapping("/admin")
public class AdministrationController {
    @GetMapping({"", "/"})
    public ModelAndView homeAdmin(Model model) {
        ModelAndView mav = new ModelAndView("admin/index");
        mav.addObject("title", "ACCUEIL - CONTAINERS TRACKING");
        mav.addObject("view", "admin/home");
        mav.addObject("isHome", true);
        return mav;
    }
}
