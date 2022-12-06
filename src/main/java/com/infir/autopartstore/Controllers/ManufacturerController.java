package com.infir.autopartstore.Controllers;

import com.infir.autopartstore.CheckRoles;
import com.infir.autopartstore.Models.Manufacturer;
import com.infir.autopartstore.Models.Stamp;
import com.infir.autopartstore.Repos.ManufacturerRepos;
import com.infir.autopartstore.Repos.StampRepos;
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
@RequestMapping("/employManufactur")
@PreAuthorize("hasAnyAuthority('EMPLOYEE')")
public class ManufacturerController {
    @Autowired
    private ManufacturerRepos manufacturerRepos;

    @Autowired
    private StampRepos stampRepos;

    @GetMapping()
    public String employManufacturList(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        Iterable<Manufacturer> manufacturers = manufacturerRepos.findAll();
        model.addAttribute("manufacturers",manufacturers);
        Iterable<Stamp> stamps = stampRepos.findAll();
        model.addAttribute("stamps",stamps);
        return "Manufacturer/main";
    }

    @GetMapping("/add")
    public String employManufacturAdd(Manufacturer manufacturer, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        Iterable<Stamp> stamps = stampRepos.findAll();
        model.addAttribute("stamps",stamps);
        return "Manufacturer/add";
    }

    @PostMapping("/add")
    public String employManufacturAdd(@ModelAttribute("manufacturer")@Valid Manufacturer manufacturer,
                              BindingResult bindingResult, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if(bindingResult.hasErrors()){
            Iterable<Stamp> stamps = stampRepos.findAll();
            model.addAttribute("stamps",stamps);
            return "Manufacturer/add";
        }
        manufacturerRepos.save(manufacturer);
        return "redirect:";
    }

    @GetMapping("/edit/{manufacturer}")
    public String employManufacturEdit(Manufacturer manufacturer, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        Iterable<Stamp> stamps = stampRepos.findAll();
        model.addAttribute("stamps",stamps);
        return "Manufacturer/edit";
    }

    @PostMapping("/edit/{manufacturer}")
    public String employManufacturPostEdit(
            @ModelAttribute("manufacturer") @Valid Manufacturer manufacturer,
            BindingResult bindingResult, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if (bindingResult.hasErrors()) {
            Iterable<Stamp> stamps = stampRepos.findAll();
            model.addAttribute("stamps",stamps);
            return "Manufacturer/edit";
        }
        manufacturerRepos.save(manufacturer);
        return "redirect:../";
    }

    @GetMapping("/del/{manufacturer}")
    public String employManufacturDel(
            Manufacturer manufacturer, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        manufacturerRepos.delete(manufacturer);
        return "redirect:../";
    }
}