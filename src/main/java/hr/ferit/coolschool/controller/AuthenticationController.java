package hr.ferit.coolschool.controller;

import hr.ferit.coolschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @GetMapping("auth")
    public UserDetails getDetails(){
        return this.userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }


}
