package org.launchcode.TestProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController extends AbstractController{

    @RequestMapping
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("username", returnLoginName(request));
        model.addAttribute("login", returnLoginURL(request));
        return "index";
    }


}
