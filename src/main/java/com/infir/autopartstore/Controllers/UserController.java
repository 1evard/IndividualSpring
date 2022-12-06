package com.infir.autopartstore.Controllers;

import com.infir.autopartstore.CheckRoles;
import com.infir.autopartstore.Models.Role;
import com.infir.autopartstore.Models.User;
import com.infir.autopartstore.Repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepos userRepository;

    @GetMapping
    public String userList(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        model.addAttribute("users", userRepository.findActive());
        return "User/usermain";
    }

    @GetMapping("/add")
    public String userAdd(User user, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        model.addAttribute("roles", Role.values());
        return "User/useradd";
    }

    @PostMapping("/add")
    public String userAddSave(
            @ModelAttribute("user") @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", Role.values());
            return "User/useradd";
        }
        user.setActive(true);
        userRepository.save(user);
        return "redirect:";
    }

    @GetMapping("/edit/{user}")
    public String userEdit(User user, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        model.addAttribute("roles", Role.values());
        return "User/useredit";
    }

    @PostMapping("/edit/{user}")
    public String userEditSave(
            @ModelAttribute("user") @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", Role.values());
            return "User/useredit";
        }
        userRepository.save(user);
        return "redirect:../";
    }

    @GetMapping("/del/{user}")
    @PreAuthorize("isAuthenticated()")
    public String userDel(
            User user, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        user.setActive(false);
        userRepository.save(user);
        return "redirect:../";
    }
}