package com.jinho.springboot.todowebapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authenticate(String username, String password) {
        boolean isValidUserName = username.equals("jinho");
        boolean isValidPassword = password.equals("asd");
        return isValidUserName&&isValidPassword;
    }
}
