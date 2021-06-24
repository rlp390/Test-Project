package org.launchcode.TestProject.controllers;

import org.launchcode.TestProject.models.User.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AbstractController {

    @Autowired
    AuthenticationController authenticationController;

    public String returnLoginName(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        String str = "";

        if(user == null) {
            str = "Not logged in | ";
        } else {
            str = "Logged in as " + user.getUsername() + " | ";
        }

        System.out.println("Str:  " + str);
        return str;
    }

    public String returnLoginURL(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        String str = "";

        if(user == null) {
            str = "Login";
        } else {
            str = "Logout";
        }

        System.out.println("Str:  " + str);
        return str;
    }
}
