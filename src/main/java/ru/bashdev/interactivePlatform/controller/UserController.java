package ru.bashdev.interactivePlatform.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bashdev.interactivePlatform.models.User;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping()
    public User getAllUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
