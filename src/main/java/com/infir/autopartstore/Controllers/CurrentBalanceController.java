package com.infir.autopartstore.Controllers;

import com.infir.autopartstore.CheckRoles;
import com.infir.autopartstore.Models.CurrentBalance;
import com.infir.autopartstore.Repos.CurrentBalanceRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/employCurrentBalance")
@PreAuthorize("hasAnyAuthority('EMPLOYEE')")
public class CurrentBalanceController {
    @Autowired
    private CurrentBalanceRepos currentBalanceRepos;

    @GetMapping()
    public String curBalList(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        Iterable<CurrentBalance> currentBalances = currentBalanceRepos.findAll();
        model.addAttribute("currentBalances",currentBalances);
        return "CurrentBalance/main";
    }

    @GetMapping("/add")
    public String curBalAdd(CurrentBalance currentBalance, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        return "CurrentBalance/add";
    }

    @PostMapping("/add")
    public String curBalAdd(@ModelAttribute("currentBalance")@Valid CurrentBalance currentBalance,
                              BindingResult bindingResult, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if(bindingResult.hasErrors()){
            return "CurrentBalance/add";
        }
        currentBalanceRepos.save(currentBalance);
        return "redirect:";
    }

    @GetMapping("/edit/{currentBalance}")
    public String curBalEdit(CurrentBalance currentBalance, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        return "CurrentBalance/edit";
    }

    @PostMapping("/edit/{currentBalance}")
    public String curBalPostEdit(
            @ModelAttribute("currentBalance") @Valid CurrentBalance currentBalance,
            BindingResult bindingResult, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if (bindingResult.hasErrors()) {
            return "CurrentBalance/edit";
        }
        currentBalanceRepos.save(currentBalance);
        return "redirect:../";
    }

    @GetMapping("/del/{currentBalance}")
    public String curBalDel(
            CurrentBalance currentBalance,Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        currentBalanceRepos.delete(currentBalance);
        return "redirect:../";
    }
}
