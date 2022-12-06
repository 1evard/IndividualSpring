package com.infir.autopartstore.Controllers;

import com.infir.autopartstore.CheckRoles;
import com.infir.autopartstore.Models.Client;
import com.infir.autopartstore.Models.Employee;
import com.infir.autopartstore.Models.Post;
import com.infir.autopartstore.Models.User;
import com.infir.autopartstore.Repos.EmployeeRepos;
import com.infir.autopartstore.Repos.PostRepos;
import com.infir.autopartstore.Repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/adminEmployee")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class EmployeeController {

    @Autowired
    private UserRepos userRepository;

    @Autowired
    private EmployeeRepos employeeRepos;

    @Autowired
    private PostRepos postRepos;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String employeeList(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        model.addAttribute("employeers", employeeRepos.findAll());
        return "Employee/employee-main";
    }

    @GetMapping("/add")
    public String employeeAdd(Employee employee, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("posts", postRepos.findAll());
        return "Employee/employee-add";
    }

    @PostMapping("/add")
    public String employeeAdd(@ModelAttribute("employee")@Valid Employee employee,
                            BindingResult bindingResult, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if(bindingResult.hasErrors()){
            model.addAttribute("users", userRepository.findAll());
            return "Employee/employee-add";
        }
        employeeRepos.save(employee);
        return "redirect:/adminEmployee";
    }

    @GetMapping("/edit/{employee}")
    @PreAuthorize("isAuthenticated()")
    public String employeeEdit(Employee employee, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("posts", postRepos.findAll());
        return "Employee/employee-edit";
    }

    @PostMapping("/edit/{employee}")
    @PreAuthorize("isAuthenticated()")
    public String employeePostEdit(
            @ModelAttribute("employee")@Valid Employee employee,
            BindingResult bindingResult,
            Model model
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userRepository.findAll());
            return "Employee/employee-edit";
        }
        employeeRepos.save(employee);
        return "redirect:../";
    }

    @GetMapping("/show/{employee}")
    public String employeeShow(
            Employee employee, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        return "Employee/employee-detail";
    }

    @GetMapping("/del/{employee}")
    @PreAuthorize("isAuthenticated()")
    public String employeeDel(
            Employee employee, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        employeeRepos.delete(employee);
        return "redirect:../";
    }
}