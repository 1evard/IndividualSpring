package com.infir.autopartstore.Controllers;

import com.infir.autopartstore.CheckRoles;
import com.infir.autopartstore.Models.ProductType;
import com.infir.autopartstore.Models.Stamp;
import com.infir.autopartstore.Repos.ProductTypeRepos;
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
@RequestMapping("/employeeStamp")
@PreAuthorize("hasAnyAuthority('EMPLOYEE')")
public class StampController {
    @Autowired
    private StampRepos stampRepos;

    @Autowired
    private ProductTypeRepos productTypeRepos;

    @GetMapping()
    public String stampList(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        Iterable<Stamp> stamps = stampRepos.findAll();
        model.addAttribute("stamps",stamps);
        Iterable<ProductType> productTypes = productTypeRepos.findAll();
        model.addAttribute("productTypes", productTypes);

        return "Stamp/main";
    }

    @GetMapping("/add")
    public String stampAdd(Stamp stamp, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        Iterable<ProductType> productTypes = productTypeRepos.findAll();
        model.addAttribute("productTypes", productTypes);
        return "Stamp/add";
    }

    @PostMapping("/add")
    public String stampAdd(@ModelAttribute("stamp")@Valid Stamp stamp,
                              BindingResult bindingResult, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if(bindingResult.hasErrors()){
            Iterable<ProductType> productTypes = productTypeRepos.findAll();
            model.addAttribute("productTypes", productTypes);
            return "Stamp/add";
        }
        stampRepos.save(stamp);
        return "redirect:";
    }

    @GetMapping("/edit/{stamp}")
    public String stampEdit(Stamp stamp, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        Iterable<ProductType> productTypes = productTypeRepos.findAll();
        model.addAttribute("productTypes", productTypes);
        return "Stamp/edit";
    }

    @PostMapping("/edit/{stamp}")
    public String stampPostEdit(
            @ModelAttribute("stamp") @Valid Stamp stamp,
            BindingResult bindingResult, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if (bindingResult.hasErrors()) {
            Iterable<ProductType> productTypes = productTypeRepos.findAll();
            model.addAttribute("productTypes", productTypes);
            return "Stamp/edit";
        }
        stampRepos.save(stamp);
        return "redirect:../";
    }

    @GetMapping("/del/{stamp}")
    public String stampDel(
            Stamp stamp, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        stampRepos.delete(stamp);
        return "redirect:../";
    }
}
