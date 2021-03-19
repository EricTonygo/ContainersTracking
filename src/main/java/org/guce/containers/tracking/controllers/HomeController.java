/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author penda
 */
@Controller
public class HomeController {
    @GetMapping({"", "/"})
    public ModelAndView homeAdmin(Model model) {
        ModelAndView mav = new ModelAndView("home/index");
        mav.addObject("title", "ACCUEIL - CONTAINERS TRACKING");
        mav.addObject("view", "home/home");
        mav.addObject("isHome", true);
        return mav;
    }
}
