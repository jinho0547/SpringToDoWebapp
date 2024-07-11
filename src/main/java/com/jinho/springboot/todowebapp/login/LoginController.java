package com.jinho.springboot.todowebapp.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {

    @Autowired
    AuthenticationService authservice;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginId(String name, String password, ModelMap model) {
        if(authservice.authenticate(name,password)) {
            model.put("name", name);
            return "/welcome";
        }
        model.put("error", "Invalid username or password");
        return "/login";
    }
}
