package com.infir.autopartstore.Controllers;

import com.infir.autopartstore.CheckRoles;
import com.infir.autopartstore.Models.ProductType;
import com.infir.autopartstore.Models.Stamp;
import com.infir.autopartstore.Models.StampCar;
import com.infir.autopartstore.Repos.ProductTypeRepos;
import com.infir.autopartstore.Repos.StampCarRepos;
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
@RequestMapping("/employeeStampCar")
@PreAuthorize("hasAnyAuthority('EMPLOYEE')")
public class StampCarController {
    @Autowired
    private StampCarRepos stampCarRepos;


    @GetMapping()
    public String stampcarList(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        Iterable<StampCar> stampCars = stampCarRepos.findAll();
        model.addAttribute("stampCars",stampCars);

        return "StampCar/main";
    }

    @GetMapping("/add")
    public String stampcarAdd(StampCar stampCar, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        return "StampCar/add";
    }

    @PostMapping("/add")
    public String stampcarAdd(@ModelAttribute("stampCar")@Valid StampCar stampCar,
                           BindingResult bindingResult, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if(bindingResult.hasErrors()){
            return "StampCar/add";
        }
        stampCarRepos.save(stampCar);
        return "redirect:";
    }

    @GetMapping("/edit/{stampCar}")
    public String stampcarEdit(StampCar stampCar, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        return "StampCar/edit";
    }

    @PostMapping("/edit/{stampCar}")
    public String stampcarPostEdit(
            @ModelAttribute("stampCar") @Valid StampCar stampCar,
            BindingResult bindingResult, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if (bindingResult.hasErrors()) {
            return "StampCar/edit";
        }
        stampCarRepos.save(stampCar);
        return "redirect:../";
    }

    @GetMapping("/del/{stampCar}")
    public String stampcarDel(
            StampCar stampCar, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        stampCarRepos.delete(stampCar);
        return "redirect:../";
    }
}
