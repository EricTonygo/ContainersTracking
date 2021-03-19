/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.containers.tracking.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author penda
 */
@Controller
public class ContainersTrackingErrorController implements ErrorController{
    
    @GetMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("errors/index");
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return new ModelAndView("redirect:/error/404");
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return new ModelAndView("redirect:/error/500");
            }
        }
        mav.addObject("title", "ERROR PAGE - CONTAINERS TRACKING");
        mav.addObject("view", "errors/others");
        mav.addObject("isError", true);
        return mav;
    }
    
    @GetMapping("/error/404")
    public ModelAndView error404() {
        ModelAndView mav = new ModelAndView("errors/index");
        mav.addObject("title", "PAGE NOT FOUND - CONTAINERS TRACKING");
        mav.addObject("view", "errors/404");
        mav.addObject("is404", true);
        return mav;
    }
    
    @GetMapping("/error/500")
    public ModelAndView error500() {
        ModelAndView mav = new ModelAndView("errors/index");
        mav.addObject("title", "INTERNAL SERVER ERROR - CONTAINERS TRACKING");
        mav.addObject("view", "errors/500");
        mav.addObject("is500", true);
        return mav;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
    
}
