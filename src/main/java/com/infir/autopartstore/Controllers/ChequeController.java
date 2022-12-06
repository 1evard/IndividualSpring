package com.infir.autopartstore.Controllers;

import com.infir.autopartstore.CheckRoles;
import com.infir.autopartstore.Models.*;
import com.infir.autopartstore.Repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/employCheque")
public class ChequeController {
    @Autowired
    private ChequeRepos chequeRepos;

    @Autowired
    private ProductsRepos productsRepos;

    @Autowired
    private ClientRepos clientRepos;

    @Autowired
    private EmployeeRepos employeeRepos;

    @GetMapping()
    public String cheqList(Model model){
        Iterable<Cheque> cheques = chequeRepos.findAll();
        model.addAttribute("cheques",cheques);
        Iterable<Products> products = productsRepos.findAll();
        model.addAttribute("products",products);
        Iterable<Client> clients = clientRepos.findAll();
        model.addAttribute("clients",clients);
        Iterable<Employee> employees = employeeRepos.findAll();
        model.addAttribute("employees",employees);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        return "Cheque/main";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE')")
    public String cheqAdd(Cheque cheque, Model model){
        Iterable<Products> products = productsRepos.findAll();
        model.addAttribute("products",products);
        Iterable<Client> clients = clientRepos.findAll();
        model.addAttribute("clients",clients);
        Iterable<Employee> employees = employeeRepos.findAll();
        model.addAttribute("employees",employees);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        return "Cheque/add";
    }

    @Autowired
    private CurrentBalanceRepos currentBalanceRepos;

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE')")
    public String cheqAdd(@ModelAttribute("cheque")@Valid Cheque cheque,
                          BindingResult bindingResult,
                          Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if(bindingResult.hasErrors()){
            return "Cheque/add";
        }

        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm a"));
        cheque.setTimesell(time);
        chequeRepos.save(cheque);
        return "redirect:";
    }

    @GetMapping("/edit/{cheque}")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE')")
    public String cheqEdit(Cheque cheque, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        Iterable<Products> products = productsRepos.findAll();
        model.addAttribute("products",products);
        Iterable<Client> clients = clientRepos.findAll();
        model.addAttribute("clients",clients);
        Iterable<Employee> employees = employeeRepos.findAll();
        model.addAttribute("employees",employees);
        return "Cheque/edit";
    }

    @PostMapping("/edit/{cheque}")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE')")
    public String cheqPostEdit(
            @ModelAttribute("cheque") @Valid Cheque cheque,
            BindingResult bindingResult, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if (bindingResult.hasErrors()) {
            return "Cheque/edit";
        }
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm a"));
        cheque.setTimesell(time);
        chequeRepos.save(cheque);
        return "redirect:../";
    }

    @GetMapping("/del/{cheque}")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE')")
    public String cheqDel(
            Cheque cheque, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        chequeRepos.delete(cheque);
        return "redirect:../";
    }
}
