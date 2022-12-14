package com.infir.autopartstore.Controllers;

import com.infir.autopartstore.Models.Role;
import com.infir.autopartstore.Models.User;
import com.infir.autopartstore.Repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepos userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model){
        User userFromDb = userRepository.findByLogin(user.getLogin());
        if(userFromDb != null)
        {
            model.addAttribute("message", "Пользователь с таким логином уже зарегистрирован");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER)); //Для создания АДМИНА поменяй сдесь на "ADMIN" зарегай его а потом поменяй обратно на "USER"
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }
}