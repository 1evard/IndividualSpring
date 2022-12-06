package com.infir.autopartstore.Controllers;

import com.infir.autopartstore.CheckRoles;
import com.infir.autopartstore.Models.ProductContent;
import com.infir.autopartstore.Repos.ProductContentRepos;
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
@RequestMapping("/adminProdС")
@PreAuthorize("hasAnyAuthority('EMPLOYEE')")
public class ProductsContentController {
    @Autowired
    private ProductContentRepos productContentRepos;

    @GetMapping()
    public String prodContList(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        Iterable<ProductContent> productContents = productContentRepos.findAll();
        model.addAttribute("productContents",productContents);
        return "ProductContent/main";
    }

    @GetMapping("/add")
    public String prodContAdd(ProductContent productContent, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        return "ProductContent/add";
    }

    @PostMapping("/add")
    public String prodContAdd(@ModelAttribute("productContent")@Valid ProductContent productContent,
                          BindingResult bindingResult, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if(bindingResult.hasErrors()){
            return "ProductContent/add";
        }
        productContentRepos.save(productContent);
        return "redirect:";
    }

    @GetMapping("/edit/{productContent}")
    public String prodContEdit(ProductContent productContent, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        return "ProductContent/edit";
    }

    @PostMapping("/edit/{productContent}")
    public String prodContPostEdit(
            @ModelAttribute("productContent") @Valid ProductContent productContent,
            BindingResult bindingResult, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        if (bindingResult.hasErrors()) {
            return "ProductContent/edit";
        }
        productContentRepos.save(productContent);
        return "redirect:../";
    }

    @GetMapping("/del/{productContent}")
    public String prodContDel(
            ProductContent productContent, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isUser", new CheckRoles().userCheck(auth));
        model.addAttribute("isAdmin", new CheckRoles().adminCheck(auth));
        model.addAttribute("isEmployee", new CheckRoles().employeeCheck(auth));
        productContentRepos.delete(productContent);
        return "redirect:../";
    }
}
